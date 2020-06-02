package com.itstyle.database.domain.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author 10097454
 * @Date 2020/05/27
 * @Description TODO
 */
public class RefundOrderVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 退款订单号
     */
    private String refundorderid;

    /**
     * 支付订单号
     */
    private String payorderid;

    /**
     * 渠道支付单号
     */
    private String channelpayorderno;

    /**
     * 商户ID
     */
    private String mchid;

    /**
     * 商户退款单号
     */
    private String mchrefundno;

    /**
     * 渠道ID
     */
    private String channelid;

    /**
     * 支付金额,单位分
     */
    private Long payamount;

    /**
     * 退款金额,单位分
     */
    private Long refundamount;

    /**
     * 退款状态:0-订单生成,1-退款中,2-退款成功,3-退款失败,4-业务处理完成
     */
    private Integer status;

    /**
     * 退款结果:0-不确认结果,1-等待手动处理,2-确认成功,3-确认失败
     */
    private Integer result;

    /**
     * 客户端IP
     */
    private String clientip;

    /**
     * 设备
     */
    private String device;

    /**
     * 备注
     */
    private String remarkinfo;

    /**
     * 渠道用户标识,如微信openId,支付宝账号
     */
    private String channeluser;

    /**
     * 用户姓名
     */
    private String username;

    /**
     * 渠道商户ID
     */
    private String channelmchid;

    /**
     * 渠道错误码
     */
    private String channelerrcode;

    /**
     * 渠道错误描述
     */
    private String channelerrmsg;

    /**
     * 特定渠道发起时额外参数
     */
    private String extra;

    /**
     * 通知地址
     */
    private String notifyurl;

    /**
     * 扩展参数1
     */
    private String param1;

    /**
     * 扩展参数2
     */
    private String param2;

    /**
     * 订单失效时间
     */
    private Date expiretime;

    /**
     * 订单退款成功时间
     */
    private Date refundsucctime;

    private Date createtime;

    private Date updatetime;
    private String payId;

    public String getRefundorderid() {
        return refundorderid;
    }

    public void setRefundorderid(String refundorderid) {
        this.refundorderid = refundorderid;
    }

    public String getPayorderid() {
        return payorderid;
    }

    public void setPayorderid(String payorderid) {
        this.payorderid = payorderid;
    }

    public String getChannelpayorderno() {
        return channelpayorderno;
    }

    public void setChannelpayorderno(String channelpayorderno) {
        this.channelpayorderno = channelpayorderno;
    }

    public String getMchid() {
        return mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }

    public String getMchrefundno() {
        return mchrefundno;
    }

    public void setMchrefundno(String mchrefundno) {
        this.mchrefundno = mchrefundno;
    }

    public String getChannelid() {
        return channelid;
    }

    public void setChannelid(String channelid) {
        this.channelid = channelid;
    }

    public Long getPayamount() {
        return payamount;
    }

    public void setPayamount(Long payamount) {
        this.payamount = payamount;
    }

    public Long getRefundamount() {
        return refundamount;
    }

    public void setRefundamount(Long refundamount) {
        this.refundamount = refundamount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getClientip() {
        return clientip;
    }

    public void setClientip(String clientip) {
        this.clientip = clientip;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getRemarkinfo() {
        return remarkinfo;
    }

    public void setRemarkinfo(String remarkinfo) {
        this.remarkinfo = remarkinfo;
    }

    public String getChanneluser() {
        return channeluser;
    }

    public void setChanneluser(String channeluser) {
        this.channeluser = channeluser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getChannelmchid() {
        return channelmchid;
    }

    public void setChannelmchid(String channelmchid) {
        this.channelmchid = channelmchid;
    }

    public String getChannelerrcode() {
        return channelerrcode;
    }

    public void setChannelerrcode(String channelerrcode) {
        this.channelerrcode = channelerrcode;
    }

    public String getChannelerrmsg() {
        return channelerrmsg;
    }

    public void setChannelerrmsg(String channelerrmsg) {
        this.channelerrmsg = channelerrmsg;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getNotifyurl() {
        return notifyurl;
    }

    public void setNotifyurl(String notifyurl) {
        this.notifyurl = notifyurl;
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

    public Date getExpiretime() {
        return expiretime;
    }

    public void setExpiretime(Date expiretime) {
        this.expiretime = expiretime;
    }

    public Date getRefundsucctime() {
        return refundsucctime;
    }

    public void setRefundsucctime(Date refundsucctime) {
        this.refundsucctime = refundsucctime;
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

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }
}
