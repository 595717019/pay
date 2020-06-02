package com.itstyle.modules.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayResponse;
import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.demo.trade.model.ExtendParams;
import com.alipay.demo.trade.model.builder.AlipayTradePayRequestBuilder;
import com.alipay.demo.trade.model.builder.AlipayTradePrecreateRequestBuilder;
import com.alipay.demo.trade.model.builder.AlipayTradeQueryRequestBuilder;
import com.alipay.demo.trade.model.builder.AlipayTradeRefundRequestBuilder;
import com.alipay.demo.trade.model.result.AlipayF2FPayResult;
import com.alipay.demo.trade.model.result.AlipayF2FPrecreateResult;
import com.alipay.demo.trade.model.result.AlipayF2FQueryResult;
import com.alipay.demo.trade.model.result.AlipayF2FRefundResult;
import com.alipay.demo.trade.utils.ZxingUtils;
import com.itstyle.common.constants.Constants;
import com.itstyle.common.model.Product;
import com.itstyle.common.utils.CommonUtil;
import com.itstyle.modules.alipay.util.AliPayConfig;
import com.itstyle.modules.service.IPayService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AlipayService implements IPayService {
    private static final Logger logger = LoggerFactory.getLogger(AlipayService.class);
    private static String notify_url;

    public static String getNotify_url() {
        return notify_url;
    }

    @Value("${alipay.notify.url}")
    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    @Override
    public String pay(Product product) {
        logger.info("订单号：{}生成支付宝支付码", product.getOutTradeNo());
        String message = Constants.SUCCESS;
        String imgPath = Constants.QRCODE_PATH + Constants.SF_FILE_SEPARATOR + product.getOutTradeNo() + ".png";

        // (必填) 商户门店编号，通过门店号和商家后台可以配置精准到门店的折扣信息，详询支付宝技术支持
        String storeId = "test_store_id";
        ExtendParams extendParams = new ExtendParams();
        extendParams.setSysServiceProviderId("2088221367429114");
        String timeoutExpress = "120m";
        String totalAmount = CommonUtil.divide(product.getTotalFee().toString(), "100").toString();

        // 创建扫码支付请求builder，设置请求参数
        AlipayTradePrecreateRequestBuilder builder = new AlipayTradePrecreateRequestBuilder()
                .setAppAuthToken(product.getAppAuthToken())
                .setSubject(product.getSubject())
                .setTotalAmount(totalAmount)
                .setOutTradeNo(product.getOutTradeNo())
                .setSellerId("")
                .setBody(product.getBody())//128长度 --附加信息
                .setStoreId(storeId)
                .setExtendParams(extendParams)
                .setTimeoutExpress(timeoutExpress)
                .setNotifyUrl(notify_url);//支付宝服务器主动通知商户服务器里指定的页面http路径,根据需要设置

        AlipayF2FPrecreateResult result = AliPayConfig.getAlipayTradeService().tradePrecreate(builder);
        switch (result.getTradeStatus()) {
            case SUCCESS:
                logger.info("支付宝预下单成功: )");

                AlipayTradePrecreateResponse response = result.getResponse();
                dumpResponse(response);
                ZxingUtils.getQRCodeImge(response.getQrCode(), 256, imgPath);
                break;

            case FAILED:
                logger.info("支付宝预下单失败!!!");
                message = Constants.FAIL;
                break;

            case UNKNOWN:
                logger.info("系统异常，预下单状态未知!!!");
                message = Constants.FAIL;
                break;

            default:
                logger.info("不支持的交易状态，交易返回异常!!!");
                message = Constants.FAIL;
                break;
        }
        return message;
    }

    @Override
    public String tradePay(Product product) {
        logger.info("订单号：{}生成支付宝支付码", product.getOutTradeNo());
        String storeId = "test_store_id";
        ExtendParams extendParams = new ExtendParams();
        extendParams.setSysServiceProviderId("2088221367429114");
        String timeoutExpress = "120m";
        String totalAmount = CommonUtil.divide(product.getTotalFee().toString(), "100").toString();
        String message = Constants.SUCCESS;

        // 创建扫码支付请求builder，设置请求参数
        AlipayTradePayRequestBuilder builder = new AlipayTradePayRequestBuilder()
                .setAppAuthToken(product.getAppAuthToken())
                .setAuthCode(product.getAuthCode())
                .setSubject(product.getSubject())
                .setTotalAmount(totalAmount)
                .setOutTradeNo(product.getOutTradeNo())
                .setSellerId("")
                .setBody(product.getBody())//128长度 --附加信息
                .setStoreId(storeId)
                .setExtendParams(extendParams)
                .setTimeoutExpress(timeoutExpress)
                .setNotifyUrl(notify_url);//支付宝服务器主动通知商户服务器里指定的页面http路径,根据需要设置

        AlipayF2FPayResult result = AliPayConfig.getAlipayTradeService().tradePay(builder);
        switch (result.getTradeStatus()) {
            case SUCCESS:
                logger.info("支付宝下单成功: )");

                AlipayTradePayResponse response = result.getResponse();
                dumpResponse(response);
                break;

            case FAILED:
                logger.info("支付宝下单失败!!!");
                message = Constants.FAIL;
                break;

            case UNKNOWN:
                logger.info("系统异常，下单状态未知!!!");
                message = Constants.FAIL;
                break;

            default:
                logger.info("不支持的交易状态，交易返回异常!!!");
                message = Constants.FAIL;
                break;
        }
        return message;
    }

    @Override
    public String orderquery(Product product) {
        logger.info("订单号：{}订单查询", product.getOutTradeNo());
        String message = Constants.SUCCESS;

        AlipayTradeQueryRequestBuilder builder = new AlipayTradeQueryRequestBuilder()
                .setOutTradeNo(product.getOutTradeNo()).setAppAuthToken(product.getAppAuthToken());

        AlipayF2FQueryResult result = AliPayConfig.getAlipayTradeService().queryTradeResult(builder);
        AlipayTradeQueryResponse response = result.getResponse();
        switch (result.getTradeStatus()) {
            case SUCCESS:
                dumpResponse(response);
                message = response.getTradeStatus();
                break;

            case FAILED:
                message = response.getSubMsg();
                break;

            case UNKNOWN:
                message = response.getSubMsg();
                break;

            default:
                message = response.getSubMsg();
                break;
        }
        return message;
    }

    @Override
    public String refund(Product product,String ordNo) {
        logger.info("订单号：" + product.getOutTradeNo() + "支付宝退款");
        String message = Constants.SUCCESS;

        String refundAmount = CommonUtil.divide(product.getTotalFee().toString(), "100").toString();
        String refundReason = "正常退款，用户买多了";
        String storeId = "test_store_id";

        // 创建退款请求builder，设置请求参数
        AlipayTradeRefundRequestBuilder builder = new AlipayTradeRefundRequestBuilder()
                .setOutTradeNo(product.getOutTradeNo())
                .setRefundAmount(refundAmount)
                .setRefundReason(refundReason)
                .setStoreId(storeId);

        AlipayF2FRefundResult result = AliPayConfig.getAlipayTradeService().tradeRefund(builder);
        switch (result.getTradeStatus()) {
            case SUCCESS:
                logger.info("支付宝退款成功: )");
                break;

            case FAILED:
                logger.info("支付宝退款失败!!!");
                message = Constants.FAIL;
                break;

            case UNKNOWN:
                logger.info("系统异常，订单退款状态未知!!!");
                message = Constants.FAIL;
                break;

            default:
                logger.info("不支持的交易状态，交易返回异常!!!");
                message = Constants.FAIL;
                break;
        }
        return message;
    }

    @Override
    public void setSubMchId(Product product) {

    }

    @Override
    public short getPayType(Product product) {
        return (short) 1;
    }

    @Override
    public JSONObject poundage(String authToken, String outTradeNo) {
        return null;
    }

    @Override
    public String refundQuery(Product product) {
        return null;
    }

    // 简单打印应答
    private void dumpResponse(AlipayResponse response) {
        if (response != null) {
            logger.info(String.format("code:%s, msg:%s", response.getCode(), response.getMsg()));
            if (StringUtils.isNotEmpty(response.getSubCode())) {
                logger.info(String.format("subCode:%s, subMsg:%s", response.getSubCode(), response.getSubMsg()));
            }
            logger.info("body:" + response.getBody());
        }
    }
}
