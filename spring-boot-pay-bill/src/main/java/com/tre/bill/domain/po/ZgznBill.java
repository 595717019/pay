package com.tre.bill.domain.po;

import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author JDev
 * @since 2020-05-22
 */
public class ZgznBill implements Serializable {

    private static final long serialVersionUID = 1L;

    private String mchid;

    private String orderid;

    private String outtradeno;

    private String channelid;

    private String totalfee;

    private Double recfeeamt;

    private Double recfeerate;

    private Integer status;

    private Date createtime;

    private Date paysucctime;

    public String getMchid() {
        return mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }
    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
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
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
    public Date getPaysucctime() {
        return paysucctime;
    }

    public void setPaysucctime(Date paysucctime) {
        this.paysucctime = paysucctime;
    }

    @Override
    public String toString() {
        return "ZgznBill{" +
        "mchid=" + mchid +
        ", orderid=" + orderid +
        ", outtradeno=" + outtradeno +
        ", channelid=" + channelid +
        ", totalfee=" + totalfee +
        ", recfeeamt=" + recfeeamt +
        ", recfeerate=" + recfeerate +
        ", status=" + status +
        ", createtime=" + createtime +
        ", paysucctime=" + paysucctime +
        "}";
    }
}
