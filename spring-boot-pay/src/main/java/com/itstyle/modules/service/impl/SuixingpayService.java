package com.itstyle.modules.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alipay.demo.trade.utils.ZxingUtils;
import com.itstyle.common.constants.Constants;
import com.itstyle.common.model.Product;
import com.itstyle.common.utils.CommonUtil;
import com.itstyle.modules.service.IPayService;
import com.itstyle.modules.suixingpay.util.ApiRequestBean;
import com.itstyle.modules.suixingpay.util.ConfigSuiXing;
import com.itstyle.modules.suixingpay.util.HttpUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedHashMap;

@Component
public class SuixingpayService implements IPayService {
    private static final Logger logger = LoggerFactory.getLogger(SuixingpayService.class);
    private static String notify_url;
    private static String refund_notify_url;//申请退款回调

    public static String getNotify_url() {
        return notify_url;
    }

    @Value("${suixingpay.notify.url}")
    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public static String getRefund_notify_url() {
        return refund_notify_url;
    }

    @Value("${suixingpay.refund_notify.url}")
    public void setRefund_notify_url(String refund_notify_url) {
        this.refund_notify_url = refund_notify_url;
    }

    @Override
    public String pay(Product product) {
        logger.info("订单号：{}生成随行付扫码付支付码", product.getOutTradeNo());
        String amt = CommonUtil.divide(product.getTotalFee().toString(), "100").toString();
        String imgPath = Constants.QRCODE_PATH + Constants.SF_FILE_SEPARATOR + product.getOutTradeNo() + ".png";
        String message = Constants.SUCCESS;

        ApiRequestBean<JSONObject> reqBean = new ApiRequestBean<JSONObject>();
        ConfigSuiXing.commonParams(reqBean);
        JSONObject reqData = new JSONObject();
        //业务参数
        reqData.put("mno", product.getAppAuthToken()); //传入参数 商户编号
        reqData.put("ordNo", product.getOutTradeNo()); //商户订单号,只支持数字，字母，下划线，横线
        reqData.put("subMechId", ""); //子商户号
        reqData.put("amt", amt); //订单总金额
        reqData.put("payType", product.getTradeType()); //支付渠道
        reqData.put("subject", product.getSubject()); //订单标题
        reqData.put("tradeSource", "02"); //交易来源 01服务商，02收银台，03硬件
        reqData.put("trmIp", product.getSpbillCreateIp());
        reqData.put("notifyUrl", notify_url); //回调地址
        reqBean.setReqData(reqData);

        String reqStr = ConfigSuiXing.sign(reqBean);
        System.out.println("随行付扫码付,请求参数:" + reqStr);
        String resultJson = HttpUtils.connectPostUrl(ConfigSuiXing.UNIFIED_ORDER_URL, reqStr);
        System.out.println("随行付扫码付,返回信息：" + resultJson);

        //不要对reqData排序 所以用LinkedHashMap
        HashMap<String, Object> result = JSON.parseObject(resultJson, LinkedHashMap.class, Feature.OrderedField);
        if ("0000".equals(result.get("code"))) {
            logger.info("随行付预扫码付，{}下单成功", product.getOutTradeNo());
            JSONObject json = new JSONObject(result);
            ZxingUtils.getQRCodeImge(json.getJSONObject("respData").getString("payUrl"), 256, imgPath);
        } else {
            logger.info("随行付预扫码付，{}下单失败", product.getOutTradeNo());
            message = Constants.FAIL;
        }
        return message;
    }

    @Override
    public String tradePay(Product product) {
        logger.info("订单号：{}随行付条码付", product.getOutTradeNo());

        String amt = CommonUtil.divide(product.getTotalFee() == null ? "0" : product.getTotalFee().toString(), "100").toString();
//        String message = Constants.SUCCESS;

        ApiRequestBean<JSONObject> reqBean = new ApiRequestBean<JSONObject>();
        //封装通用参数
        ConfigSuiXing.commonParams(reqBean);
        JSONObject reqData = new JSONObject();
        //业务参数
        reqData.put("mno", product.getAppAuthToken()); //传入参数 商户编号
        reqData.put("ordNo", product.getOutTradeNo()); //商户订单号
        reqData.put("subMechId", ""); //子商户号
        reqData.put("authCode", product.getAuthCode()); //授权码
        reqData.put("amt", amt); //订单总金额
        reqData.put("payType", product.getTradeType()); //支付渠道
        reqData.put("subject", product.getSubject()); //订单标题
        reqData.put("tradeSource", "02"); //交易来源 01服务商，02收银台，03硬件
        reqData.put("trmIp", product.getSpbillCreateIp());
        reqBean.setReqData(reqData);

        String reqStr = ConfigSuiXing.sign(reqBean);
        String message = reverseScan(reqStr, product);

        return message;
    }

    /* 随行付条码付 */
    private String reverseScan(String reqStr, Product product) {
        String message = Constants.FAIL;
        logger.info("随行付条码付，请求参数:{}", reqStr);
        String outTradeNo = product.getOutTradeNo();//流水号
        String resultJson = HttpUtils.connectPostUrl(ConfigSuiXing.MICRO_URL, reqStr);
        if (resultJson == null || resultJson == "") {
            logger.info("随行付条码付，{}请求接口失败", outTradeNo);
            return message;
        } else {
            logger.info("随行付条码付，返回结果：{}", resultJson);
        }

        //不要对reqData排序 所以用LinkedHashMap
        HashMap<String, Object> result = JSON.parseObject(resultJson, LinkedHashMap.class, Feature.OrderedField);

        if (result.get("respData") != null) {
            JSONObject resp = JSON.parseObject(result.get("respData").toString());
            if ("0000".equals(result.get("code")) && resp.getString("bizCode").equals("0000")) {
                message = Constants.SUCCESS;
                logger.info("随行付条码付，{}下单成功", outTradeNo);
            } else if ("0000".equals(result.get("code")) && resp.getString("bizCode").equals("2002")) { //用户支付中，请稍后进行查询
                logger.info("随行付条码付，{}支付中:{}", outTradeNo, resp.getString("bizMsg"));
                int reqNum = 1;
                message = getOrderInfo(product, reqNum);//条码付，每隔2秒钟查询一次订单
            } else {
                logger.info("随行付条码付，{}下单失败:{}", outTradeNo, resp.getString("bizMsg"));
                message = Constants.FAIL;
            }
        } else {
            logger.info("随行付条码付，{}下单失败:{}", outTradeNo, result.get("msg").toString());
            message = Constants.FAIL;
        }
        return message;
    }

    @Override
    public String orderquery(Product product) {
        logger.info("随行付订单查询：{}", product.getOutTradeNo());
        String message = Constants.SUCCESS;
        ApiRequestBean<JSONObject> reqBean = new ApiRequestBean<JSONObject>();
        //封装通用参数
        ConfigSuiXing.commonParams(reqBean);
        JSONObject reqData = new JSONObject();
        //业务参数
        reqData.put("mno", product.getAppAuthToken()); //传入参数 商户编号
        reqData.put("ordNo", product.getOutTradeNo()); //商户订单号
        reqBean.setReqData(reqData);

        String reqStr = ConfigSuiXing.sign(reqBean);
        System.out.println("随行付订单查询请求参数:" + reqStr);
        String resultJson = HttpUtils.connectPostUrl(ConfigSuiXing.CHECK_ORDER_URL, reqStr);
        System.out.println("随行付订单查询返回信息：" + resultJson);
        HashMap<String, Object> result = JSON.parseObject(resultJson, LinkedHashMap.class, Feature.OrderedField);
        if ("0000".equals(result.get("code"))) {
            JSONObject json = new JSONObject(result);
            message = CommonUtil.convertMessage(json.getJSONObject("respData").getString("tranSts"));
            logger.info("随行付订单:{}查询成功:{}", product.getOutTradeNo(), message);
        } else {
            logger.info("随行付订单{}查询失败:{}", product.getOutTradeNo(), result.get("msg"));
            message = Constants.FAIL;
        }
        return message;
    }

    /**
     * @param product
     * @return {@link java.lang.String}
     * @Author 10097454
     * @Date 2020/05/25
     * @Description 条码付，每隔2秒钟查询一次订单
     **/
    private String getOrderInfo(Product product, int reqNum) {
        String message = Constants.SUCCESS;
        try {
            Thread.sleep(2000);//休眠 2s
            message = orderquery(product);
            logger.info("条码付，每隔2秒钟查询一次订单,第[{}]次,{}", reqNum, message);
            if (!message.equals(Constants.SUCCESS)) {//不是成功状态
                reqNum = reqNum + 1;
                if (reqNum >= 10) {
                    logger.info("条码付，每隔2秒钟查询一次订单,达到[{}]次,查询终止", reqNum);
                    message = Constants.FAIL;
                } else {
                    message = getOrderInfo(product, reqNum);
                }
            }
        } catch (InterruptedException e) {
            message = Constants.FAIL;
            e.printStackTrace();
        } finally {
            return message;
        }
    }

    /**
     * @Author 10097454
     * @Date 2020/05/26
     * @Description 随行付申请退款接口
     **/
    @Override
    public String refund(Product product, String ordNo) {
//        String ordNo = (int) (Math.random()*1000000000) + "" + (int) (Math.random()*1000000000);
        logger.info("随行付退款：原订单号:{}", product.getOutTradeNo(), "\t 退款订单号：", ordNo);
        String amt = CommonUtil.divide(product.getTotalFee().toString(), "100").toString();
        String message = Constants.SUCCESS;

        ApiRequestBean<JSONObject> reqBean = new ApiRequestBean<JSONObject>();
        ConfigSuiXing.commonParams(reqBean);
        JSONObject reqData = new JSONObject();
        //业务参数
        reqData.put("mno", product.getAppAuthToken()); //传入参数 商户编号
        reqData.put("ordNo", ordNo); //商户订单号,只支持数字，字母，下划线，横线
        reqData.put("amt", amt); //订单总金额
        reqData.put("origOrderNo", product.getOutTradeNo()); //原交易商户订单号
        reqData.put("notifyUrl", refund_notify_url); //申请退款回调地址
        reqBean.setReqData(reqData);

        String reqStr = ConfigSuiXing.sign(reqBean);
        logger.info("随行付退款请求参数:" + reqStr);
        String resultJson = HttpUtils.connectPostUrl(ConfigSuiXing.REFUND_URL, reqStr);
        logger.info("随行付退款返回信息：" + resultJson);
        if (StringUtils.isBlank(resultJson)) {
            logger.info("请求随行付申请退款接口失败");
            message = Constants.FAIL;
            return message;
        }
        HashMap<String, Object> result = JSON.parseObject(resultJson, LinkedHashMap.class, Feature.OrderedField);

        if (result.get("respData") != null) {
            JSONObject resp = JSON.parseObject(result.get("respData").toString());
            if ("0000".equals(result.get("code")) && resp.getString("bizCode").equals("0000")) {
                message = Constants.SUCCESS;
                //注：此成功仅代表操作成功，需调查询接口查询最终结果
                logger.info("随行付退款操作成功");
            } else if ("0000".equals(result.get("code")) && resp.getString("bizCode").equals("2002")) {//退款中，请稍后进行查询退款状态
                message = Constants.SUCCESS;
                logger.info("随行付退款操作:{}", resp.getString("bizMsg"));
            } else if ("0000".equals(result.get("code")) && resp.getString("bizCode").equals("1003")) {//退货金额超出可退金额
                message = Constants.FAIL;
                logger.info("随行付退款操作:{}", resp.getString("bizMsg"));
            } else {
                message = Constants.FAIL;
                logger.info("随行付退款操作:{}", resp.getString("bizMsg"));
            }
        } else {
            logger.info("随行付退款操作:{}", result.get("msg").toString());
            message = Constants.FAIL;
        }
        return message;
    }

    @Override
    public void setSubMchId(Product product) {

    }

    @Override
    public short getPayType(Product product) {
        return (short) 2;
    }

    /**
     * @return {@link java.lang.String}
     * @Author 10097454
     * @Date 2020/05/15
     * @Description 手续费查询接口
     **/
    @Override
    public JSONObject poundage(String mno, String ordNo) {
        logger.info("订单号：{}随行付手续费查询", ordNo);
        String message = Constants.SUCCESS;
        ApiRequestBean<JSONObject> reqBean = new ApiRequestBean<JSONObject>();
        ConfigSuiXing.commonParams(reqBean);
        JSONObject reqData = new JSONObject();
        reqData.put("mno", mno); //传商户编号
        reqData.put("ordNo", ordNo); //商户订单号
        reqBean.setReqData(reqData);

        String reqStr = ConfigSuiXing.sign(reqBean);
        System.out.println("随行付手续费查询参数:" + reqStr);
        String resultJson = HttpUtils.connectPostUrl(ConfigSuiXing.POUNDAGE_URL, reqStr);
        System.out.println("随行付手续费查询返回信息：" + resultJson);

        //不要对reqData排序 所以用LinkedHashMap
        HashMap<String, Object> result = JSON.parseObject(resultJson, LinkedHashMap.class, Feature.OrderedField);
        JSONObject resp = null;
        JSONObject resJson = new JSONObject();
        if (result.get("respData") != null) {
            resp = JSON.parseObject(result.get("respData").toString());
            if ("0000".equals(result.get("code")) && resp.getString("bizCode").equals("0000")) {
                logger.info("商户：{}\t订单：{}手续费查询成功", mno, ordNo);
            } else {
                logger.info("商户：{}\t订单：{}手续费查询失败", mno, ordNo);
                message = Constants.FAIL;
                resJson.put("code", "-1");
            }
        } else {
            message = Constants.FAIL;
            resJson.put("code", "-1");
        }
        resJson.put("message", message);
        resJson.put("respData", resp);
        return resJson;
    }

    /**
     * @Author 10097454
     * @Date 2020/05/28
     * @Description 退款查询接口
     **/
    @Override
    public String refundQuery(Product product) {
        String refundorderid = product.getRefundorderid();
        logger.info("随行付退款查询：{}", refundorderid);
        String message = Constants.SUCCESS;
        ApiRequestBean<JSONObject> reqBean = new ApiRequestBean<JSONObject>();
        //封装通用参数
        ConfigSuiXing.commonParams(reqBean);
        JSONObject reqData = new JSONObject();
        //业务参数
        reqData.put("mno", product.getAppAuthToken()); //传入参数 商户编号
        reqData.put("ordNo", refundorderid); //商户退款订单号
        reqBean.setReqData(reqData);

        String reqStr = ConfigSuiXing.sign(reqBean);
        System.out.println("随行付退款查询请求参数:" + reqStr);
        String resultJson = HttpUtils.connectPostUrl(ConfigSuiXing.REFUNDQUERY_URL, reqStr);
        System.out.println("随行付退款查询返回信息：" + resultJson);
        HashMap<String, Object> result = JSON.parseObject(resultJson, LinkedHashMap.class, Feature.OrderedField);
        if ("0000".equals(result.get("code"))) {
            JSONObject json = new JSONObject(result);
            message = CommonUtil.convertMessage(json.getJSONObject("respData").getString("tranSts"));
            logger.info("随行付退款订单:{}查询成功:{}", refundorderid, message);
        } else {
            message = Constants.FAIL;
            logger.info("随行付退款订单:{}查询失败:{}", refundorderid, result.get("msg"));
        }
        return message;
    }
}
