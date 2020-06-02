package com.tre.bill.domain.po;

import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 支付渠道
 * </p>
 *
 * @author JDev
 * @since 2020-05-22
 */
public class PayChannel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 渠道ID
     */
    private String channelid;

    /**
     * 商户ID
     */
    private String mchid;

    /**
     * 支付ID
     */
    private Integer payid;

    /**
     * 支付渠道
     */
    private String tradetype;

    /**
     * 商户渠道token
     */
    private String apptoken;

    /**
     * 支付渠道状态,0-停止使用,1-使用中
     */
    private Integer state;

    private Date createtime;

    private Date updatetime;

    public String getChannelid() {
        return channelid;
    }

    public void setChannelid(String channelid) {
        this.channelid = channelid;
    }
    public String getMchid() {
        return mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }
    public Integer getPayid() {
        return payid;
    }

    public void setPayid(Integer payid) {
        this.payid = payid;
    }
    public String getTradetype() {
        return tradetype;
    }

    public void setTradetype(String tradetype) {
        this.tradetype = tradetype;
    }
    public String getApptoken() {
        return apptoken;
    }

    public void setApptoken(String apptoken) {
        this.apptoken = apptoken;
    }
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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
        return "PayChannel{" +
        "channelid=" + channelid +
        ", mchid=" + mchid +
        ", payid=" + payid +
        ", tradetype=" + tradetype +
        ", apptoken=" + apptoken +
        ", state=" + state +
        ", createtime=" + createtime +
        ", updatetime=" + updatetime +
        "}";
    }
}
