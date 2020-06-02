package com.itstyle.common.model;

import java.io.Serializable;

/**
 * 产品订单信息
 * 创建者 科帮网
 * 创建时间	2017年7月27日
 */
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    private String productId;// 商品ID
    private String subject;//订单名称
    private String body;// 商品描述
    private Integer totalFee;// 总金额(单位是分)
    private String outTradeNo;// 订单号(唯一)
    private String spbillCreateIp;// 发起人IP地址
    private String attach;// 附件数据主要用于商户携带订单的自定义数据
    private String payType;// 支付类型(1:支付宝 2:微信 3:银联)
    private Short payWay;// 支付方式 (1：PC,平板 2：手机)
    private String frontUrl;// 前台回调地址  非扫码支付使用
    private String tradeType; //微信 支付宝
    private String payId;
    private Double recfeeamt;//交易手续费:单位元
    private Double recfeerate;//交易手续费率:单位%
    private String refundorderid;//退款订单号

    public String getRefundorderid() {
        return refundorderid;
    }

    public void setRefundorderid(String refundorderid) {
        this.refundorderid = refundorderid;
    }

    public Double getRecfeeamt() {
        return recfeeamt;
    }

    public void setRecfeeamt(Double recfeeamt) {
        this.recfeeamt = recfeeamt;
    }

    public Double getRecfeerate() {
        return recfeerate;
    }

    public void setRecfeerate(Double recfeerate) {
        this.recfeerate = recfeerate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMchid() {
        return mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }

    private int status; //支付状态,0-订单生成,1-支付中(目前未使用),2-支付成功,3-业务处理完成
    private String mchid; //商户号
    private String appAuthToken;// 子商户
    private String authCode;// 付款码

    public Product() {
        super();
    }

    public Product(String productId, String subject, String body,
                   Integer totalFee, String outTradeNo, String spbillCreateIp,
                   String attach, String payType, Short payWay, String frontUrl, String tradeType, String payId, String appAuthToken, String authCode) {
        super();
        this.productId = productId;
        this.subject = subject;
        this.body = body;
        this.totalFee = totalFee;
        this.outTradeNo = outTradeNo;
        this.spbillCreateIp = spbillCreateIp;
        this.attach = attach;
        this.payType = payType;
        this.payWay = payWay;
        this.frontUrl = frontUrl;
        this.tradeType = tradeType;
        this.payId = payId;
        this.appAuthToken = appAuthToken;
        this.authCode = authCode;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public Short getPayWay() {
        return payWay;
    }

    public void setPayWay(Short payWay) {
        this.payWay = payWay;
    }

    public String getFrontUrl() {
        return frontUrl;
    }

    public void setFrontUrl(String frontUrl) {
        this.frontUrl = frontUrl;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getAppAuthToken() {
        return appAuthToken;
    }

    public void setAppAuthToken(String appAuthToken) {
        this.appAuthToken = appAuthToken;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }
}
