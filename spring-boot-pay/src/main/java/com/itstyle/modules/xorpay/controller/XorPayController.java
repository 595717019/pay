//package com.itstyle.modules.xorpay.controller;
//
//import com.itstyle.common.constants.Constants;
//import com.itstyle.common.model.Product;
//import com.itstyle.modules.suixingpay.service.ISuixingPayService;
//import com.itstyle.modules.xorpay.service.IXorPayService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import net.sf.json.JSONObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//
///**
// * 支付宝支付
// * 创建者 科帮网
// * 创建时间	2017年7月30日
// */
//@Api(tags ="XorPay")
//@Controller
//@RequestMapping(value = "xorpay")
//public class XorPayController {
//	private static final Logger logger = LoggerFactory.getLogger(XorPayController.class);
//	@Autowired
//	private IXorPayService xorPayService;
//
//	@ApiOperation(value="二维码支付")
//	@RequestMapping(value="qcPay",method=RequestMethod.POST)
//	public @ResponseBody JSONObject qcPay(Product product) {
//		logger.info("二维码支付");
//		JSONObject json = new JSONObject();
//		String message  =  xorPayService.xorPay(product);
//		if(Constants.SUCCESS.equals(message)){
//			String qr_code= "../qrcode/"+product.getOutTradeNo()+".png";
//			json.put("qr_code",qr_code);
//		}else{
//			//失败
//		}
//		json.put("code","ok");
//		json.put("message",message);
//		json.put("out_trade_no",product.getOutTradeNo());
//		return json;
//	}
//
//	@ApiOperation(value="条码支付")
//	@RequestMapping(value="qcTradePay",method=RequestMethod.POST)
//	public @ResponseBody JSONObject qcTradePay(Product product) {
//		logger.info("条码支付");
//		JSONObject json = new JSONObject();
//		String message  =  xorPayService.xorTradePay(product);
//		json.put("code","ok");
//		json.put("message",message);
//		json.put("out_trade_no",product.getOutTradeNo());
//		return json;
//	}
//
//	@ApiOperation(value="订单查询")
//	@RequestMapping(value="qcQuery",method=RequestMethod.POST)
//	public @ResponseBody JSONObject qcQuery(Product product) {
//		logger.info("订单查询");
//		JSONObject json = new JSONObject();
//		String message  =  xorPayService.orderquery(product);
//		json.put("code","ok");
//		json.put("message",message);
//		json.put("out_trade_no",product.getOutTradeNo());
//		return json;
//	}
//}
