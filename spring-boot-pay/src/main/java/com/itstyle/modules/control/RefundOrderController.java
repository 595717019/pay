package com.itstyle.modules.control;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.itstyle.common.constants.Constants;
import com.itstyle.common.model.Product;
import com.itstyle.common.model.ResponseResult;
import com.itstyle.database.domain.dto.OrderDTO;
import com.itstyle.database.domain.po.RefundOrder;
import com.itstyle.database.domain.vo.OrderVO;
import com.itstyle.database.domain.vo.RefundOrderVO;
import com.itstyle.database.mapper.MchInfoMapper;
import com.itstyle.database.mapper.PayOrderMapper;
import com.itstyle.database.mapper.RefundOrderMapper;
import com.itstyle.modules.service.IPayService;
import com.itstyle.modules.service.PayServiceFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 退款订单 前端控制器
 */
@Api(tags = "退款")
@RestController
@RequestMapping("/refund")
public class RefundOrderController {
    private static final Logger logger = LoggerFactory.getLogger(RefundOrderController.class);
    PayServiceFactory payServiceFactory = new PayServiceFactory();

    @Autowired
    private RefundOrderMapper refundOrderMapper;
    @Autowired
    private MchInfoMapper mchInfoMapper;
    @Autowired
    private PayOrderMapper payOrderMapper;

    /**
     * @param product
     * @return
     * @Author 10097454
     * @Date 2020/05/27
     * @Description 订单路由
     **/
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

    /**
     * @param product
     * @return
     * @Author 10097454
     * @Date 2020/05/29
     * @Description 退款订单路由
     **/
    private boolean refundOrderRoute(Product product) {
        logger.info("获取退款订单路由开始");
        boolean isFind = false;
        try {
            HashMap<String, Object> map = mchInfoMapper.refundOrderRoute(product);
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
            logger.info("获取退款订单路由结束");
            return isFind;
        }
    }

    /**
     * @Author 10097454
     * @Date 2020/05/27
     * @Description 申请退款
     **/
    @ApiOperation(value = "申请退款")
    @RequestMapping(value = "qcRefund", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject qcRefund(@RequestBody Product product) {
        logger.info("申请退款开始");
        JSONObject json = new JSONObject();
        String mchid = product.getMchid();//商户编号
        String outTradeNo = product.getOutTradeNo();//交易流水号
        int inTotalFee = product.getTotalFee() == null ? 0 : product.getTotalFee();//传入的退款金额
        if (StringUtils.isBlank(mchid)) {
            json.put("code", "error");
            json.put("message", Constants.FAIL);
            json.put("out_trade_no", outTradeNo);
            logger.info("订单:{}\t必须传入商户", outTradeNo);
            return json;
        }
        if (StringUtils.isBlank(outTradeNo)) {
            json.put("code", "error");
            json.put("message", Constants.FAIL);
            json.put("out_trade_no", outTradeNo);
            logger.info("商户:{}\t必须传入流水号", mchid);
            return json;
        }
        if (inTotalFee <= 0) {
            json.put("code", "error");
            json.put("message", Constants.FAIL);
            json.put("out_trade_no", outTradeNo);
            logger.info("商户：{}\t订单:{}\t金额：{}\t请输入正确的金额", mchid, outTradeNo, inTotalFee);
            return json;
        }

        //第一步：查看支付成功的订单是否存在
        OrderVO orderVO = new OrderVO();
        orderVO.setMchid(mchid);//商户编号
        orderVO.setOuttradeno(outTradeNo);//交易流水号
        orderVO.setStatus("2");//交易状态
        List<OrderDTO> list = payOrderMapper.getOrderList(orderVO);
        if (list.size() > 0) {//存在成功的支付订单数据
            int amt = list.get(0).getTotalfee();
            if (inTotalFee > amt) {//退款金额不正确
                json.put("code", "error");
                json.put("message", Constants.FAIL);
                json.put("out_trade_no", outTradeNo);
                logger.info("订单:{}\t退货金额超出可退金额", outTradeNo);
                return json;
            }
        } else {//没有成功的支付订单数据
            json.put("code", "error");
            json.put("message", Constants.FAIL);
            json.put("out_trade_no", outTradeNo);
            logger.info("订单:{}\t非成功态不允许发起退款", outTradeNo);
            return json;
        }

        boolean isFind = orderRoute(product);//订单路由
        if (!isFind) {//没有找到支付路由
            json.put("code", "error");
            json.put("message", Constants.FAIL);
            json.put("out_trade_no", outTradeNo);
            logger.info("商户：{}\t订单:{}\t没有找到支付路由", mchid, outTradeNo);
            return json;
        }

        //第二步：查询退款订单数据
        RefundOrderVO refundOrderVO = new RefundOrderVO();
        refundOrderVO.setMchid(mchid);//商户ID
        refundOrderVO.setMchrefundno(outTradeNo);//商户退款单号(流水号)
        logger.info("商户:{}\t订单:{}\t申请退款", product.getMchid(), product.getOutTradeNo());

        ResponseResult responseResult = selectRefundOrder(refundOrderVO);
        if ("0".equals(responseResult.getCode())) {//查询退款订单成功
            if (responseResult.getData() != null) {
                List<RefundOrder> refundOrders = (List<RefundOrder>) responseResult.getData();
                if (refundOrders.size() > 0) {//存在退款数据
                    int status = refundOrders.get(0).getStatus();//退款状态
                    String refundorderid = refundOrders.get(0).getRefundorderid();
                    if (status == 2) {//退款成功
                        json.put("code", "error");//2048
                        json.put("message", Constants.FAIL);
                        json.put("out_trade_no", outTradeNo);
                        logger.info("订单:{}\t当前状态不允许此交易,退款单号：{}", outTradeNo, refundorderid);
                    } else {//默认插入的退款数据，状态是0，在退款成功回调更新状态为2
                        json.put("code", "error");//0002
                        json.put("message", Constants.FAIL);
                        json.put("out_trade_no", outTradeNo);
                        logger.info("订单:{}\t退款操作失败,退款单号：{}", outTradeNo, refundorderid);
                    }
                    return json;
                }
            }
        }

        IPayService payService = payServiceFactory.create(product);
        String refundorderid = (int) (Math.random() * 1000000000) + "" + (int) (Math.random() * 1000000000);
        String message = payService.refund(product, refundorderid);//调用随行付申请退款接口，返回success或者fail字符串

        refundOrderVO.setRefundorderid(refundorderid);//退款订单号
        refundOrderVO.setPayamount(product.getTotalFee().longValue());//支付金额,单位分
        refundOrderVO.setClientip(product.getSpbillCreateIp());//客户端IP
        refundOrderVO.setPayId(product.getPayId());
        refundOrderVO.setRefundamount(product.getTotalFee().longValue());//退款金额,单位分

        return recordRefund(refundOrderVO, json, message);
    }

    /**
     * @Author 10097454
     * @Date 2020/05/26
     * @Description 记录申请退款数据
     **/
    private JSONObject recordRefund(RefundOrderVO refundOrderVO, JSONObject json, String message) {
        logger.info("记录申请退款数据开始");
        if (refundOrderMapper.insertRefund(refundOrderVO)) {
            json.put("code", "ok");
            json.put("message", message);
            json.put("out_trade_no", refundOrderVO.getRefundorderid());//返回退款单号
        } else {
            json.put("code", "error");
            json.put("message", "记录申请退款数据失败");
        }
        logger.info("记录申请退款数据结束");
        return json;
    }

    /**
     * @Author 10097454
     * @Date 2020/05/26
     * @Description 申请退款回调
     **/
    @ApiOperation(value = "随行付申请退款操作回调")
    @RequestMapping(value = "paySuixingpayRefundNotify", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject paySuixingpayRefundNotify(HttpServletRequest request) throws IOException {
        logger.info("随行付申请退款操作回调开始");
        String notifyRes = new BufferedReader(new InputStreamReader(request.getInputStream())).lines().collect(Collectors.joining(System.lineSeparator()));
        logger.info("随行付申请退款操作回调结果:{}", notifyRes);
        JSONObject jsonResp = JSON.parseObject(notifyRes);

        JSONObject json = new JSONObject();
        if (jsonResp == null) {
            logger.info("随行付申请退款操作回调结果为空");
            json.put("code", "error");
            json.put("msg", "失败");
            return json;
        }

        SimpleDateFormat formatBefore = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat formatAfter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = formatBefore.parse(jsonResp.getString("payTime"));//退款成功时间
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String refundorderid = jsonResp.getString("ordNo");//退款订单号
        String totalOffstAmt = jsonResp.getString("totalOffstAmt");//总计
        String recFeeAmt = jsonResp.getString("recFeeAmt");//手续费
        String amt = jsonResp.getString("amt");//退款金额,单位元
        int amtCent = 0;//退款金额,单位分
        if (!StringUtils.isBlank(amt)) {
            amtCent = (int) (Double.parseDouble(amt) * 100);
        }

        Map<String, String> params = new HashMap<String, String>();//更新退款订单参数
        params.put("refundorderid", refundorderid);//退款订单号
        params.put("refundsucctime", formatAfter.format(date));//订单退款成功时间
        params.put("refundamount", String.valueOf(amtCent));//退款金额,单位分
        boolean isUpdate = refundOrderMapper.updateRefund(params);
        logger.info("退款订单{}更新:{}", refundorderid, isUpdate ? "成功" : "失败");

        //更新支付订单状态
        //回调中没有支付订单号，通过退款单号查找支付订单号
        RefundOrderVO refundOrderVO = new RefundOrderVO();
        refundOrderVO.setRefundorderid(refundorderid);
        List<RefundOrder> resp = refundOrderMapper.selectRefundOrder(refundOrderVO);
        String mchrefundno = null;
        if (resp.size() > 0) {
            mchrefundno = resp.get(0).getMchrefundno();
            Map<String, String> orderParams = new HashMap<String, String>();//更新支付订单参数
            orderParams.put("outTradeNo", mchrefundno);
            orderParams.put("status", "3");
            boolean isUpdateOrder = payOrderMapper.updateOrderStatus(orderParams);
            logger.info("支付订单{}状态更新为3，:{}", refundorderid, isUpdateOrder ? "成功" : "失败");
        }

        json.put("code", isUpdate ? "success" : "fail");
        json.put("msg", "成功");
        logger.info("退款回调响应给Suixingpay:{}", "success");
        return json;
    }

    /**
     * @Author 10097454
     * @Date 2020/05/27
     * @Description 查询支付系统退款订单
     **/
    @ApiOperation(value = "支付系统退款订单查询")
    @RequestMapping(value = "selectRefundOrder", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult selectRefundOrder(@RequestBody RefundOrderVO refundOrderVO) {
        logger.info("支付系统退款订单查询开始");
        List<RefundOrder> resp = refundOrderMapper.selectRefundOrder(refundOrderVO);
        logger.info("支付系统退款查询订单结束");
        return ResponseResult.buildOK(resp);
    }

    /**
     * @Author 10097454
     * @Date 2020/05/28
     * @Description 退款订单
     **/
    @ApiOperation(value = "退款订单查询")
    @RequestMapping(value = "refundQuery", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject refundQuery(@RequestBody Product product) {
        logger.info("退款订单查询开始");
        JSONObject json = new JSONObject();
        String mchid = product.getMchid();
        String refundorderid = product.getRefundorderid();//退款订单号
        String outTradeNo = product.getOutTradeNo();//流水号

        if (StringUtils.isBlank(refundorderid)) {//退款单号为空
            json.put("code", "error");
            json.put("message", Constants.FAIL);
            json.put("out_trade_no", refundorderid);
            logger.info("商户：{}退款单号为空", mchid);
            return json;
        }

        boolean isFind = refundOrderRoute(product);//退款订单路由
        if (!isFind) {//没有找到支付路由
            json.put("code", "error");
            json.put("message", Constants.FAIL);
            json.put("out_trade_no", refundorderid);
            logger.info("商户：{}\t退款单号:{}\t退款,没有找到支付路由", mchid, refundorderid);
            return json;
        }
        IPayService payService = payServiceFactory.create(product);
        String message = payService.refundQuery(product);

        json.put("code", "ok");
        json.put("message", message);
        json.put("out_trade_no", refundorderid);
        return json;
    }
}
