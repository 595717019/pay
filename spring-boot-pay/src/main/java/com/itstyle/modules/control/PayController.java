package com.itstyle.modules.control;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.itstyle.common.constants.Constants;
import com.itstyle.common.model.Product;
import com.itstyle.common.model.ResponseResult;
import com.itstyle.common.utils.CommonUtil;
import com.itstyle.database.domain.dto.Channel;
import com.itstyle.database.domain.vo.StatusVO;
import com.itstyle.database.mapper.MchInfoMapper;
import com.itstyle.database.mapper.PayOrderMapper;
import com.itstyle.modules.service.IPayService;
import com.itstyle.modules.service.PayServiceFactory;
import com.itstyle.modules.service.impl.SuixingpayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Api(tags = "支付入口")
@Controller
@RequestMapping(value = "pay")
public class PayController {
    private static final Logger logger = LoggerFactory.getLogger(PayController.class);
    PayServiceFactory payServiceFactory = new PayServiceFactory();

    @Autowired
    private MchInfoMapper mchInfoMapper;
    @Autowired
    private PayOrderMapper payOrderMapper;

    /* 二维码支付 */
    @ApiOperation(value = "二维码支付")
    @RequestMapping(value = "qcPay", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject qcPay(@RequestBody Product product) {
        logger.info("二维码支付");
        String orderNum = CommonUtil.createOrderNum(product);
        product.setOutTradeNo(orderNum);

        JSONObject json = new JSONObject();
        String mchid = product.getMchid();
        String outTradeNo = product.getOutTradeNo(); //商户订单号
        String payType = product.getPayType();
        int totalFee = product.getTotalFee() == null ? 0 : product.getTotalFee(); //订单总金额
        String subject = product.getSubject(); //订单标题
        String body = product.getBody();
        logger.info("订单：{}\t支付宝微信:{}\t金额：{}\t标题：{}\tbody:{}\t二维码支付,请求参数", outTradeNo, payType, totalFee, subject, body);
        if (StringUtils.isBlank(mchid) || StringUtils.isBlank(outTradeNo) || StringUtils.isBlank(payType) || StringUtils.isBlank(subject) || StringUtils.isBlank(body) || totalFee <= 0) {
            json.put("code", "error");
            json.put("message", Constants.FAIL);
            json.put("out_trade_no", outTradeNo);
            logger.info("订单：{}\t支付宝微信:{}\t金额：{}\t标题：{}\tbody:{}\t二维码支付,缺少必须参数", outTradeNo, payType, totalFee, subject, body);
            return json;
        }
        boolean isFind = payRoute(product);
        if (!isFind) {//没有找到支付路由
            json.put("code", "error");
            json.put("message", Constants.FAIL);
            json.put("out_trade_no", product.getOutTradeNo());
            logger.info("商户：{}\t订单:{}\t二维码支付,没有找到支付路由", product.getMchid(), product.getOutTradeNo());
            return json;
        }
        IPayService payService = payServiceFactory.create(product);
        String message = payService.pay(product);

        if (Constants.SUCCESS.equals(message)) {
            String qr_code = "/qrcode/" + product.getOutTradeNo() + ".png";
            json.put("qr_code", qr_code);
        } else {
            product.setStatus(-1);
        }

        return recordOrder(product, json, message);
    }

    private JSONObject recordOrder(Product product, JSONObject json, String message) {
        if (payOrderMapper.insert(product)) {
            json.put("code", "ok");
            json.put("message", message);
            json.put("out_trade_no", product.getOutTradeNo());
        } else {
            json.put("code", "error");
            json.put("message", "记录账单失败。");
        }
        return json;
    }

    /* 条码支付 */
    @ApiOperation(value = "条码支付")
    @RequestMapping(value = "qcTradePay", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject qcTradePay(@RequestBody Product product) {
        logger.info("条码支付");
        //生成支付订单号
        String orderNum = CommonUtil.createOrderNum(product);
        product.setOutTradeNo(orderNum);

        JSONObject json = new JSONObject();
        String mchid = product.getMchid();
        String outTradeNo = product.getOutTradeNo(); //商户订单号
        String authCode = product.getAuthCode(); //授权码
        int totalFee = product.getTotalFee() == null ? 0 : product.getTotalFee(); //订单总金额
        String subject = product.getSubject(); //订单标题
        String body = product.getBody();
        logger.info("订单：{}\t授权码:{}\t金额：{}\t标题：{}\tbody:{}\t条码支付,请求参数", outTradeNo, authCode, totalFee, subject, body);
        if (StringUtils.isBlank(mchid) || StringUtils.isBlank(outTradeNo) || StringUtils.isBlank(authCode) || StringUtils.isBlank(subject) || StringUtils.isBlank(body) || totalFee <= 0) {
            json.put("code", "error");
            json.put("message", Constants.FAIL);
            json.put("out_trade_no", outTradeNo);
            logger.info("订单：{}\t授权码:{}\t金额：{}\t标题：{}\tbody:{}\t条码支付,缺少必须参数", outTradeNo, authCode, totalFee, subject, body);
            return json;
        }

        boolean isFind = payRoute(product);
        if (!isFind) {//没有找到支付路由
            json.put("code", "error");
            json.put("message", Constants.FAIL);
            json.put("out_trade_no", product.getOutTradeNo());
            logger.info("商户：{}\t订单:{}\t条码支付,没有找到支付路由", mchid, outTradeNo);
            return json;
        }

        IPayService payService = payServiceFactory.create(product);
        String message = payService.tradePay(product);

        if (Constants.SUCCESS.equals(message)) {
            product.setStatus(2);
            updateRecfee(product);//更新交易手续费和交易手续费费率
        } else {
            product.setStatus(-1);
        }

        return recordOrder(product, json, message);
    }

    /* 订单查询 */
    @ApiOperation(value = "订单查询")
    @RequestMapping(value = "qcQuery", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject qcQuery(@RequestBody Product product) {
        logger.info("订单查询");
        JSONObject json = new JSONObject();
        String mchid = product.getMchid();
        String outTradeNo = product.getOutTradeNo(); //商户订单号
        if (StringUtils.isBlank(mchid) || StringUtils.isBlank(outTradeNo)) {
            json.put("code", "error");
            json.put("message", Constants.FAIL);
            json.put("out_trade_no", outTradeNo);
            logger.info("商户：{}\t订单:{}\t随行付订单查询,缺少必须参数", mchid, outTradeNo);
            return json;
        }
        boolean isFind = orderRoute(product);
        if (!isFind) {//没有找到支付路由
            json.put("code", "error");
            json.put("message", Constants.FAIL);
            json.put("out_trade_no", product.getOutTradeNo());
            logger.info("商户：{}\t订单:{}\t订单查询,没有找到支付路由", product.getMchid(), product.getOutTradeNo());
            return json;
        }
        IPayService payService = payServiceFactory.create(product);
        String message = payService.orderquery(product);

        json.put("code", "ok");
        json.put("message", message);
        json.put("out_trade_no", product.getOutTradeNo());
        return json;
    }

    /* 支付路由 */
    private boolean payRoute(Product product) {
        logger.info("获取支付路由开始");
        boolean isFind = false;
        try {
            HashMap<String, Object> map = mchInfoMapper.payinfo(product);
            if (map == null || map.isEmpty()) {
                return false;
            }
            product.setPayType(map.get("name").toString());
            product.setTradeType(map.get("tradetype").toString());
            product.setAppAuthToken(map.get("apptoken").toString());
            product.setPayId(map.get("payid").toString());
            isFind = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            logger.info("获取支付路由结束");
            return isFind;
        }
    }

    /* 支付宝回调 */
    @RequestMapping(value = "payAliNotify", method = RequestMethod.POST)
    public @ResponseBody
    String payAliNotify(HttpServletRequest request) throws ServletException, IOException {
        String notifyRes = doAliNotify(request);
        logger.info("响应给支付宝:{}", notifyRes);
        return notifyRes;
    }

    public String doAliNotify(HttpServletRequest request) throws ServletException, IOException {
        String logPrefix = "【支付宝支付回调通知】";
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
            params.put(name, valueStr);
        }
        logger.info("{}通知请求数据:reqStr={}", logPrefix, params);
        if (params.isEmpty()) {
            logger.error("{}请求参数为空", logPrefix);
            return Constants.FAIL;
        }
        payOrderMapper.update(params);
        return Constants.SUCCESS;
    }

    /* 微信回调 */
    @RequestMapping(value = "payWxNotify", method = RequestMethod.POST)
    public @ResponseBody
    String payWxNotify(HttpServletRequest request) throws ServletException, IOException {
        String logPrefix = "【微信支付回调通知】";
        String xmlResult = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
        logger.info("{}通知请求数据:reqStr={}", logPrefix, xmlResult);
        return Constants.SUCCESS;
    }

    /* Xorpay回调 */
    @RequestMapping(value = "payXorpayNotify", method = RequestMethod.POST)
    public @ResponseBody
    String payXorpayNotify(HttpServletRequest request) throws ServletException, IOException {
        String notifyRes = doXorpayNotify(request);
        logger.info("响应给Xorpay:{}", notifyRes);
        return notifyRes;
    }

    public String doXorpayNotify(HttpServletRequest request) throws ServletException, IOException {
        String logPrefix = "【Xorpay支付回调通知】";
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
            params.put(name, valueStr);
        }
        logger.info("{}通知请求数据:reqStr={}", logPrefix, params);
        if (params.isEmpty()) {
            logger.error("{}请求参数为空", logPrefix);
            return Constants.FAIL;
        }
        payOrderMapper.update(params);
        return Constants.SUCCESS;
    }

    /* 随行付回调 */
    @RequestMapping(value = "paySuixingpayNotify", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject paySuixingpayNotify(HttpServletRequest request) throws ServletException, IOException {
        JSONObject json = new JSONObject();
        String notifyRes = doSuixingpayNotify(request);

        logger.info("响应给Suixingpay:{}", notifyRes);
        json.put("code", "success");
        json.put("msg", "成功");
        return json;
    }

    public String doSuixingpayNotify(HttpServletRequest request) throws ServletException, IOException {
        String logPrefix = "【Suixingpay支付回调通知】";
        String notifyRes = new BufferedReader(new InputStreamReader(request.getInputStream())).lines().collect(Collectors.joining(System.lineSeparator()));
        logger.info("Suixingpay支付回调结果:{}", notifyRes);
        JSONObject json = JSON.parseObject(notifyRes);
        if (json == null) {
            return Constants.FAIL;
        }
        Map<String, String> params = new HashMap<String, String>();
        SimpleDateFormat formatBefore = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat formatAfter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = formatBefore.parse(json.getString("payTime"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        params.put("pay_time", formatAfter.format(date));
        params.put("order_id", json.getString("ordNo"));

        //手续费查询
        String mno = json.getString("mno");
        String ordNo = json.getString("ordNo");
        logger.info("随行付扫码支付,手续费查询参数.mno:{},ordNo:{}", mno, ordNo);
        JSONObject poundage = getPoundage(mno, ordNo);
        if (poundage != null) {
            JSONObject respData = poundage.getJSONObject("respData");
            logger.info("随行付扫码支付,手续费查询respData:{}", respData);
            if (respData != null) {
                params.put("recfeerate", respData.getString("recFeeRate"));
                params.put("recfeeamt", respData.getString("recFeeAmt"));
            }
        }
        payOrderMapper.update(params);
        return Constants.SUCCESS;
    }

    /* 手续费查询接口 */
    @ApiOperation(value = "手续费查询")
    @RequestMapping(value = "getPoundage", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject getPoundage(String mno, String ordNo) {
        String logPrefix = "【Suixingpay手续费查询接口】";
        IPayService payService = new SuixingpayService();
        JSONObject poundageRes = payService.poundage(mno, ordNo);

        JSONObject json = new JSONObject();
        if (Constants.SUCCESS.equals(poundageRes.get("message"))) {
            logger.info("随行付手续费查询成功:{}", poundageRes);
        } else {
            logger.info("随行付手续费查询失败:{}", poundageRes);
        }
        return poundageRes;
    }

    /* 支付渠道列表 */
    @ApiOperation(value = "支付渠道列表")
    @RequestMapping(value = "getChannelList", method = RequestMethod.POST)
    public @ResponseBody
    ResponseResult getChannelList() {
        logger.info("支付渠道列表");
        List<Channel> channel = payOrderMapper.getChannelList();
        return ResponseResult.buildOK(channel);
    }

    /* 订单查询路由 */
    private boolean orderRoute(Product product) {
        logger.info("获取支付路由开始");
        boolean isFind = false;
        try {
            HashMap<String, Object> map = mchInfoMapper.orderRoute(product);
            if (map == null || map.isEmpty()) {
                return false;
            }
            product.setPayType(map.get("name").toString());
            product.setTradeType(map.get("tradetype").toString());
            product.setAppAuthToken(map.get("apptoken").toString());
            product.setPayId(map.get("payid").toString());
            isFind = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            logger.info("获取支付路由结束");
            return isFind;
        }
    }

    /*更新支付渠道状态*/
    @ApiOperation(value = "更新支付渠道状态")
    @RequestMapping(value = "updateChannelStatus", method = RequestMethod.POST)
    public @ResponseBody
    ResponseResult updateChannelStatus(@RequestBody StatusVO statusVO) {
        logger.info("更新支付渠道状态");
        boolean isUpdate = payOrderMapper.updateChannelStatus(statusVO);
        return ResponseResult.buildOK(isUpdate);
    }

    /*更新交易手续费和交易手续费费率*/
    @ApiOperation(value = "获取和更新交易手续费和交易手续费费率")
    @RequestMapping(value = "updateRecfee", method = RequestMethod.POST)
    public @ResponseBody
    void updateRecfee(@RequestBody Product product) {
        logger.info("获取和更新交易手续费和交易手续费费率开始");
        boolean isUpdate = false;
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(60000);      //休眠1分钟
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //手续费查询
                String mno = product.getAppAuthToken();
                String ordNo = product.getOutTradeNo();
                logger.info("条码支付,手续费查询参数.mno:{},ordNo:{}", mno, ordNo);
                JSONObject poundage = getPoundage(mno, ordNo);
                if (poundage != null) {
                    String respData = poundage.getString("respData");
                    logger.info("条码支付,手续费查询respData:{}", respData);
                    JSONObject resp = JSON.parseObject(respData);
                    product.setRecfeeamt(resp.getDouble("recFeeAmt"));
                    product.setRecfeerate(resp.getDouble("recFeeRate"));
                    //更新手续费
                    boolean isUpdate = payOrderMapper.updateRecfee(product);
                    logger.info("条码支付,更新交易手续费和交易手续费费率:{}", isUpdate ? "成功" : "失败");
                }
            }
        });
        t.start();
    }

}
