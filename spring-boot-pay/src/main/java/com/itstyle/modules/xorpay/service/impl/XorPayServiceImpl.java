//package com.itstyle.modules.xorpay.service.impl;
//
//import com.alibaba.fastjson.JSONObject;
//import com.alipay.demo.trade.utils.ZxingUtils;
//import com.itstyle.common.constants.Constants;
//import com.itstyle.common.model.Product;
//import com.itstyle.common.utils.CommonUtil;
//import com.itstyle.common.utils.MapUtil;
//import com.itstyle.modules.suixingpay.util.ConfigSuiXing;
//import com.itstyle.modules.xorpay.service.IXorPayService;
//import com.itstyle.modules.xorpay.util.ConfigXorPay;
//import com.itstyle.modules.xorpay.util.HttpUtils;
//import com.itstyle.modules.xorpay.util.MD5utils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// *
// */
//@Component
//public class XorPayServiceImpl implements IXorPayService {
//	private static final Logger logger = LoggerFactory.getLogger(XorPayServiceImpl.class);
//
//	@Value("${alipay.notify.url}")
//	private String notify_url;
//
//
//	@Override
//	public String xorPay(Product product) {
//		String name = product.getSubject();
//		String payType = "";//ConfigXorPay.payType(product.getPayType());
//		String price = CommonUtil.divide(product.getTotalFee(), "100").toString();
//		String orderId = product.getOutTradeNo();
//		String imgPath= Constants.QRCODE_PATH+Constants.SF_FILE_SEPARATOR+product.getOutTradeNo()+".png";
//		String  message = Constants.SUCCESS;
//
//		String aid = product.getAppAuthToken().split(",")[0];
//		String appSecret = product.getAppAuthToken().split(",")[1];
//		String sign = MD5utils.generate(name + payType + price + orderId + notify_url + appSecret);
//
//
//		Map<String,String> map = new HashMap<String, String>();
//		map.put("name", name);
//		map.put("pay_type", payType);
//		map.put("price", price);
//		map.put("order_id",  orderId);
//		map.put("notify_url", notify_url);
//		map.put("sign", sign);
//
//		System.out.println("request: "+ MapUtil.getMapToString(map));
//		String post = HttpUtils.post(ConfigXorPay.UNIFIED_ORDER_URL+aid, map);
//		System.out.println("response: "+ post);
//		JSONObject result = JSONObject.parseObject(post);
//		if("ok".equals(result.get("status"))){
//			logger.info("XorPay预下单成功: )");
//			ZxingUtils.getQRCodeImge(result.getJSONObject("info").getString("qr"), 256, imgPath);
//			message = Constants.SUCCESS;
//		}else{
//			logger.info("XorPay预下单失败!!!");
//			message = result.getString("status");
//		}
//		return message;
//	}
//
//	@Override
//	public String xorTradePay(Product product) {
//		String name = product.getSubject();
//		String payType = "";//ConfigXorPay.payTradeType(product.getPayType());
//		String price = CommonUtil.divide(product.getTotalFee(), "100").toString();
//		String orderId = product.getOutTradeNo();
//		String barcode = product.getAuthCode();
//
//		String aid = product.getAppAuthToken().split(",")[0];
//		String appSecret = product.getAppAuthToken().split(",")[1];
//		String sign = MD5utils.generate(name + payType + price + orderId + notify_url + barcode + appSecret);
//
//
//		Map<String,String> map = new HashMap<String, String>();
//		map.put("name", name);
//		map.put("pay_type", payType);
//		map.put("price", price);
//		map.put("order_id",  orderId);
//		map.put("notify_url", notify_url);
//		map.put("barcode", barcode);
//		map.put("sign", sign);
//
//		System.out.println("request: "+ MapUtil.getMapToString(map));
//		String post = HttpUtils.post(ConfigXorPay.MICRO_URL+aid, map);
//		System.out.println("response: "+ post);
//		JSONObject result = JSONObject.parseObject(post);
//
//		return result.getString("status");
//	}
//
//	@Override
//	public String xorRefund(Product product) {
//
//		return null;
//	}
//
//	@Override
//	public String orderquery(Product product) {
//		String orderId = product.getOutTradeNo();
//		String aid = product.getAppAuthToken().split(",")[0];
//		String appSecret = product.getAppAuthToken().split(",")[1];
//		String sign = MD5utils.generate(orderId + appSecret);
//		String url = ConfigXorPay.CHECK_ORDER_URL+aid+"?order_id="+orderId+"&sign="+sign;
//		String post = HttpUtils.get(url);
//		System.out.println("response: "+ post);
//		JSONObject result = JSONObject.parseObject(post);
//		return result.getString("status");
//	}
//}
