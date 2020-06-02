package com.tre.bill.domain.po;

import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author JDev
 * @since 2020-05-25
 */
public class Bill implements Serializable {

    private static final long serialVersionUID = 1L;

    private String mchid;

    private String mchname;

    private String orderid;

    private String outtradeno;

    private String source;

    private String channelid;

    private String totalfee;

    private Double recfeeamt;

    private Double recfeerate;

    private String errstatus;

    private String remark;

    private Integer status;

    private Date createtime;

    private Date updatetime;

    private Date paytime;

    private Date paysucctime;

    public String getMchid() {
        return mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }
    public String getMchname() {
        return mchname;
    }

    public void setMchname(String mchname) {
        this.mchname = mchname;
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
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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
    public String getErrstatus() {
        return errstatus;
    }

    public void setErrstatus(String errstatus) {
        this.errstatus = errstatus;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
    public Date getPaytime() {
        return paytime;
    }

    public void setPaytime(Date paytime) {
        this.paytime = paytime;
    }
    public Date getPaysucctime() {
        return paysucctime;
    }

    public void setPaysucctime(Date paysucctime) {
        this.paysucctime = paysucctime;
    }

    @Override
    public String toString() {
        return "Bill{" +
        "mchid=" + mchid +
        ", mchname=" + mchname +
        ", orderid=" + orderid +
        ", outtradeno=" + outtradeno +
        ", source=" + source +
        ", channelid=" + channelid +
        ", totalfee=" + totalfee +
        ", recfeeamt=" + recfeeamt +
        ", recfeerate=" + recfeerate +
        ", errstatus=" + errstatus +
        ", remark=" + remark +
        ", status=" + status +
        ", createtime=" + createtime +
        ", updatetime=" + updatetime +
        ", paytime=" + paytime +
        ", paysucctime=" + paysucctime +
        "}";
    }
}
