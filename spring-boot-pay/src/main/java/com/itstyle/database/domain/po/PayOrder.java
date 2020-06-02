package com.itstyle.database.domain.po;

import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 支付订单
 * </p>
 *
 * @author JDev
 * @since 2020-05-07
 */
public class PayOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 支付订单号
     */
    private String orderid;

    /**
     * 商户ID
     */
    private String mchid;

    /**
     * 商户订单号
     */
    private String outtradeno;

    /**
     * 渠道ID
     */
    private String channelid;

    /**
     * 支付金额,单位分
     */
    private String totalfee;

    /**
     * 支付状态,0-订单生成,1-支付中(目前未使用),2-支付成功,3-业务处理完成
     */
    private Integer status;

    /**
     * 客户端IP
     */
    private String clientip;

    /**
     * 设备
     */
    private String payway;

    /**
     * 商品标题
     */
    private String subject;

    /**
     * 商品描述信息
     */
    private String body;

    /**
     * 特定渠道发起时额外参数
     */
    private String extra;

    /**
     * 支付错误码
     */
    private String errcode;

    /**
     * 支付错误描述
     */
    private String errmsg;

    /**
     * 扩展参数1
     */
    private String param1;

    /**
     * 扩展参数2
     */
    private String param2;

    /**
     * 通知地址
     */
    private String notifyurl;

    /**
     * 通知次数
     */
    private Integer notifycount;

    /**
     * 最后一次通知时间
     */
    private Date lastnotifytime;

    /**
     * 订单失效时间
     */
    private Date expiretime;

    /**
     * 订单支付成功时间
     */
    private Date paysucctime;

    private Date createtime;

    private Date updatetime;
    private double recfeeamt;//交易手续费:单位元
    private double recfeerate;//交易手续费率:单位%

    public double getRecfeeamt() {
        return recfeeamt;
    }

    public void setRecfeeamt(double recfeeamt) {
        this.recfeeamt = recfeeamt;
    }

    public double getRecfeerate() {
        return recfeerate;
    }

    public void setRecfeerate(double recfeerate) {
        this.recfeerate = recfeerate;
    }

    public void setRecfeerate(Integer recfeerate) {
        this.recfeerate = recfeerate;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getMchid() {
        return mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }

    public String getOuttradeno() {
        return outtradeno;
    }

    public void setOuttradeno(String outtradeno) {
        this.outtradeno = outtradeno;
    }

    public String getChannelid() {
        return channelid;
    }

    public void setChannelid(String channelid) {
        this.channelid = channelid;
    }

    public String getTotalfee() {
        return totalfee;
    }

    public void setTotalfee(String totalfee) {
        this.totalfee = totalfee;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getClientip() {
        return clientip;
    }

    public void setClientip(String clientip) {
        this.clientip = clientip;
    }

    public String getPayway() {
        return payway;
    }

    public void setPayway(String payway) {
        this.payway = payway;
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

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }

    public String getNotifyurl() {
        return notifyurl;
    }

    public void setNotifyurl(String notifyurl) {
        this.notifyurl = notifyurl;
    }

    public Integer getNotifycount() {
        return notifycount;
    }

    public void setNotifycount(Integer notifycount) {
        this.notifycount = notifycount;
    }

    public Date getLastnotifytime() {
        return lastnotifytime;
    }

    public void setLastnotifytime(Date lastnotifytime) {
        this.lastnotifytime = lastnotifytime;
    }

    public Date getExpiretime() {
        return expiretime;
    }

    public void setExpiretime(Date expiretime) {
        this.expiretime = expiretime;
    }

    public Date getPaysucctime() {
        return paysucctime;
    }

    public void setPaysucctime(Date paysucctime) {
        this.paysucctime = paysucctime;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public String toString() {
        return "PayOrder{" +
                "orderid=" + orderid +
                ", mchid=" + mchid +
                ", outtradeno=" + outtradeno +
                ", channelid=" + channelid +
                ", totalfee=" + totalfee +
                ", status=" + status +
                ", clientip=" + clientip +
                ", payway=" + payway +
                ", subject=" + subject +
                ", body=" + body +
                ", extra=" + extra +
                ", errcode=" + errcode +
                ", errmsg=" + errmsg +
                ", param1=" + param1 +
                ", param2=" + param2 +
                ", notifyurl=" + notifyurl +
                ", notifycount=" + notifycount +
                ", lastnotifytime=" + lastnotifytime +
                ", expiretime=" + expiretime +
                ", paysucctime=" + paysucctime +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                "}";
    }
}
