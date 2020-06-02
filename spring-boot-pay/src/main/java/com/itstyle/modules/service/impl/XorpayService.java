package com.itstyle.modules.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.demo.trade.utils.ZxingUtils;
import com.itstyle.common.constants.Constants;
import com.itstyle.common.model.Product;
import com.itstyle.common.utils.CommonUtil;
import com.itstyle.common.utils.MapUtil;
import com.itstyle.modules.service.IPayService;
import com.itstyle.modules.xorpay.util.ConfigXorPay;
import com.itstyle.modules.xorpay.util.HttpUtils;
import com.itstyle.modules.xorpay.util.MD5utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class XorpayService implements IPayService {
    private static final Logger logger = LoggerFactory.getLogger(XorpayService.class);
    private static String notify_url;

    public static String getNotify_url() {
        return notify_url;
    }

    @Value("${xorpay.notify.url}")
    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    @Override
    public String pay(Product product) {
        logger.info("订单号：{}Xorpay生成付款码", product.getOutTradeNo());
        String name = product.getSubject();
        String payType = product.getTradeType();//ConfigXorPay.payType(product.getPayType());
        String price = CommonUtil.divide(product.getTotalFee().toString(), "100").toString();
        String orderId = product.getOutTradeNo();
        String imgPath = Constants.QRCODE_PATH + Constants.SF_FILE_SEPARATOR + product.getOutTradeNo() + ".png";
        String message = Constants.SUCCESS;

        String aid = product.getAppAuthToken().split(",")[0];
        String appSecret = product.getAppAuthToken().split(",")[1];
        String sign = MD5utils.generate(name + payType + price + orderId + notify_url + appSecret);


        Map<String, String> map = new HashMap<String, String>();
        map.put("name", name);
        map.put("pay_type", payType);
        map.put("price", price);
        map.put("order_id", orderId);
        map.put("order_uid", "");
        map.put("notify_url", notify_url);
        map.put("sign", sign);
        payType = payType + "_barcode";

        System.out.println("request: " + MapUtil.getMapToString(map));
        String post = HttpUtils.post(ConfigXorPay.UNIFIED_ORDER_URL + aid, map);
        System.out.println("response: " + post);
        JSONObject result = JSONObject.parseObject(post);
        if ("ok".equals(result.get("status"))) {
            logger.info("XorPay预下单成功: )");
            ZxingUtils.getQRCodeImge(result.getJSONObject("info").getString("qr"), 256, imgPath);
            message = Constants.SUCCESS;
        } else {
            logger.info("XorPay预下单失败!!!");
            message = result.getString("status");
        }
        return message;
    }

    @Override
    public String tradePay(Product product) {
        String name = product.getSubject();
        String payType = product.getTradeType();//ConfigXorPay.payTradeType(product.getPayType());
        String price = CommonUtil.divide(product.getTotalFee().toString(), "100").toString();
        String orderId = product.getOutTradeNo();
        String barcode = product.getAuthCode();

        if (payType.equals("native")) {
            payType = "wechat_barcode";
        } else if (payType.equals("alipay")) {
            payType = "alipay_barcode";
        }

        String aid = product.getAppAuthToken().split(",")[0];
        String appSecret = product.getAppAuthToken().split(",")[1];
        String sign = MD5utils.generate(name + payType + price + orderId + notify_url + barcode + appSecret);


        Map<String, String> map = new HashMap<String, String>();
        map.put("name", name);
        map.put("pay_type", payType);
        map.put("price", price);
        map.put("order_id", orderId);
        map.put("notify_url", notify_url);
        map.put("barcode", barcode);
        map.put("sign", sign);

        System.out.println("request: " + MapUtil.getMapToString(map));
        String post = HttpUtils.post(ConfigXorPay.MICRO_URL + aid, map);
        System.out.println("response: " + post);
        JSONObject result = JSONObject.parseObject(post);

        return result.getString("status");
    }

    /* XorPay订单查询 */
    @Override
    public String orderquery(Product product) {
        logger.info("XorPay订单查询：{}", product.getOutTradeNo());
        String message = Constants.SUCCESS;
        String orderId = product.getOutTradeNo();
        String aid = product.getAppAuthToken().split(",")[0];
        String appSecret = product.getAppAuthToken().split(",")[1];
        String sign = MD5utils.generate(orderId + appSecret);
        String url = ConfigXorPay.CHECK_ORDER_URL + aid + "?order_id=" + orderId + "&sign=" + sign;
        String post = HttpUtils.get(url);
        logger.info("XorPay订单查询结果: " + post);
        JSONObject result = JSONObject.parseObject(post);
        if (result != null) {
            logger.info("XorPay订单查询成功!!!)");
            message = result.getString("status");
        } else {
            logger.info("XorPay订单查询失败!!!");
            message = Constants.FAIL;
        }
        return message;
    }

    @Override
    public String refund(Product product,String ordNo) {
        return null;
    }

    @Override
    public void setSubMchId(Product product) {

    }

    @Override
    public short getPayType(Product product) {
        return (short) 4;
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
