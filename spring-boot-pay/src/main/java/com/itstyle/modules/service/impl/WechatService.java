package com.itstyle.modules.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.demo.trade.utils.ZxingUtils;
import com.itstyle.common.constants.Constants;
import com.itstyle.common.model.Product;
import com.itstyle.common.utils.CommonUtil;
import com.itstyle.modules.service.IPayService;
import com.itstyle.modules.weixinpay.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

@Component
public class WechatService implements IPayService {
    private static final Logger logger = LoggerFactory.getLogger(WechatService.class);
    private static String notify_url;

    public static String getNotify_url() {
        return notify_url;
    }

    @Value("${wexinpay.notify.url}")
    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    @Override
    public String pay(Product product) {
        logger.info("订单号：{}生成微信支付码", product.getOutTradeNo());
        String message = Constants.SUCCESS;
        try {
            String imgPath = Constants.QRCODE_PATH + Constants.SF_FILE_SEPARATOR + product.getOutTradeNo() + ".png";
            String totalFee = product.getTotalFee().toString();
            totalFee = CommonUtil.subZeroAndDot(totalFee);

            SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
            ConfigUtil.commonParams(packageParams);
            packageParams.put("sub_mch_id", product.getAppAuthToken()); //TODO 子商户 传入参数
            packageParams.put("body", product.getBody());// 商品描述
            packageParams.put("out_trade_no", product.getOutTradeNo());// 商户订单号
            packageParams.put("total_fee", totalFee);// 总金额
            packageParams.put("spbill_create_ip", product.getSpbillCreateIp());// 发起人IP地址
            packageParams.put("notify_url", notify_url);// 回调地址
            packageParams.put("trade_type", "NATIVE");// 交易类型
            String sign = PayCommonUtil.createSign("UTF-8", packageParams, ConfigUtil.API_KEY);
            packageParams.put("sign", sign);// 签名

            String requestXML = PayCommonUtil.getRequestXml(packageParams);
            String resXml = HttpUtil.postData(ConfigUtil.UNIFIED_ORDER_URL, requestXML);
            Map map = XMLUtil.doXMLParse(resXml);
            String returnCode = (String) map.get("return_code");
            if ("SUCCESS".equals(returnCode)) {
                String resultCode = (String) map.get("result_code");
                if ("SUCCESS".equals(resultCode)) {
                    logger.info("订单号：{}生成微信支付码成功", product.getOutTradeNo());
                    String urlCode = (String) map.get("code_url");
                    ConfigUtil.shorturl(urlCode);//转换为短链接
                    ZxingUtils.getQRCodeImge(urlCode, 256, imgPath);// 生成二维码
                } else {
                    String errCodeDes = (String) map.get("err_code_des");
                    logger.info("订单号：{}生成微信支付码(系统)失败:{}", product.getOutTradeNo(), errCodeDes);
                    message = Constants.FAIL;
                }
            } else {
                String returnMsg = (String) map.get("return_msg");
                logger.info("(订单号：{}生成微信支付码(通信)失败:{}", product.getOutTradeNo(), returnMsg);
                message = Constants.FAIL;
            }
        } catch (Exception e) {
            logger.error("订单号：{}生成微信支付码失败(系统异常))", product.getOutTradeNo(), e);
            message = Constants.FAIL;
        }
        return message;

    }

    @Override
    public String tradePay(Product product) {
        logger.info("订单号：{}生成微信条码付", product.getOutTradeNo());
        String message = Constants.SUCCESS;
        String totalFee = product.getTotalFee().toString();
        totalFee = CommonUtil.subZeroAndDot(totalFee);

        try {
            SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
            ConfigUtil.commonParams(packageParams);
            packageParams.put("sub_mch_id", product.getAppAuthToken()); //TODO 子商户 传入参数
            packageParams.put("auth_code", product.getAuthCode()); // 用户付款码
            packageParams.put("body", product.getBody());// 商品描述
            packageParams.put("out_trade_no", product.getOutTradeNo());// 商户订单号
            packageParams.put("total_fee", totalFee);// 总金额
            packageParams.put("spbill_create_ip", product.getSpbillCreateIp());// 发起人IP地址
            String sign = PayCommonUtil.createSign("UTF-8", packageParams, ConfigUtil.API_KEY);
            packageParams.put("sign", sign);// 签名

            String requestXML = PayCommonUtil.getRequestXml(packageParams);
            String resXml = HttpUtil.postData(ConfigUtil.MICRO_URL, requestXML);
            Map map = XMLUtil.doXMLParse(resXml);
            String returnCode = (String) map.get("return_code");
            if ("SUCCESS".equals(returnCode)) {
                String resultCode = (String) map.get("result_code");
                if ("SUCCESS".equals(resultCode)) {
                    logger.info("订单号：{}微信支付成功", product.getOutTradeNo());
                } else {
                    String errCodeDes = (String) map.get("err_code_des");
                    logger.info("订单号：{}微信支付(系统)失败:{}", product.getOutTradeNo(), errCodeDes);
                    message = Constants.FAIL;
                }
            } else {
                String returnMsg = (String) map.get("return_msg");
                logger.info("(订单号：{}微信支付(通信)失败:{}", product.getOutTradeNo(), returnMsg);
                message = Constants.FAIL;
            }
        } catch (Exception e) {
            logger.error("订单号：{}微信支付失败(系统异常))", product.getOutTradeNo(), e);
            message = Constants.FAIL;
        }
        return message;
    }

    @Override
    public String orderquery(Product product) {
        String message = null;
        SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
        ConfigUtil.commonParams(packageParams);
        packageParams.put("sub_mch_id", product.getAppAuthToken()); //TODO 子商户 传入参数
        packageParams.put("out_trade_no", product.getOutTradeNo());// 商户订单号
        String sign = PayCommonUtil.createSign("UTF-8", packageParams, ConfigUtil.API_KEY);
        packageParams.put("sign", sign);// 签名

        String requestXML = PayCommonUtil.getRequestXml(packageParams);
        String resXml = HttpUtil.postData(ConfigUtil.CHECK_ORDER_URL, requestXML);
        Map map = null;
        try {
            map = XMLUtil.doXMLParse(resXml);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String returnCode = (String) map.get("return_code");
        logger.info(returnCode);
        if ("SUCCESS".equals(returnCode)) {
            String resultCode = (String) map.get("result_code");
            if ("SUCCESS".equals(resultCode)) {
                String tradeState = (String) map.get("trade_state");
                message = tradeState;
                logger.info(tradeState);
            } else {
                String errCodeDes = (String) map.get("err_code_des");
                message = errCodeDes;
                logger.info(errCodeDes);
            }
        } else {
            String returnMsg = (String) map.get("return_msg");
            message = returnMsg;
            logger.info(returnMsg);
        }

        return message;
    }

    @Override
    public String refund(Product product,String ordNo) {
        logger.info("订单号：{}微信退款", product.getOutTradeNo());
        String message = Constants.SUCCESS;
        String totalFee = product.getTotalFee().toString();
        totalFee = CommonUtil.subZeroAndDot(totalFee);

        try {
            // 账号信息
            SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
            ConfigUtil.commonParams(packageParams);
            packageParams.put("out_trade_no", product.getOutTradeNo());// 商户订单号
            packageParams.put("out_refund_no", product.getOutTradeNo());//商户退款单号
            packageParams.put("total_fee", totalFee);// 总金额
            packageParams.put("refund_fee", totalFee);//退款金额
            packageParams.put("op_user_id", ConfigUtil.MCH_ID);//操作员帐号, 默认为商户号
            String sign = PayCommonUtil.createSign("UTF-8", packageParams, ConfigUtil.API_KEY);
            packageParams.put("sign", sign);// 签名

            String requestXML = PayCommonUtil.getRequestXml(packageParams);
            String weixinPost = ClientCustomSSL.doRefund(ConfigUtil.REFUND_URL, requestXML).toString();
            Map map = XMLUtil.doXMLParse(weixinPost);
            String returnCode = (String) map.get("return_code");
            if ("SUCCESS".equals(returnCode)) {
                String resultCode = (String) map.get("result_code");
                if ("SUCCESS".equals(resultCode)) {
                    logger.info("订单号：{}微信退款成功并删除二维码", product.getOutTradeNo());
                } else {
                    String errCodeDes = (String) map.get("err_code_des");
                    logger.info("订单号：{}微信退款失败:{}", product.getOutTradeNo(), errCodeDes);
                    message = Constants.FAIL;
                }
            } else {
                String returnMsg = (String) map.get("return_msg");
                logger.info("订单号：{}微信退款失败:{}", product.getOutTradeNo(), returnMsg);
                message = Constants.FAIL;
            }
        } catch (Exception e) {
            logger.error("订单号：{}微信支付失败(系统异常)", product.getOutTradeNo(), e);
            message = Constants.FAIL;
        }
        return message;
    }

    @Override
    public void setSubMchId(Product product) {

    }

    @Override
    public short getPayType(Product product) {
        return (short) 3;
    }

    @Override
    public JSONObject poundage(String authToken, String outTradeNo) {
        return null;
    }

    @Override
    public String refundQuery(Product product) {
        return null;
    }
}
