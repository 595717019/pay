package com.tre.bill.domain.dto;

import java.util.Date;

/**
 * @Author 10097454
 * @Date 2020/05/25
 * @Description TODO
 */
public class ExceptionBillData {
    private String mchid;

    private String mchname;

    private String orderid;

    private String outtradeno;

    private String source;

    private String channelid;
    private String channelname;

    private String totalfee;

    private Double recfeeamt;

    private Double recfeerate;

    private String errstatus;

    private String remark;

    private Integer status;
    private String statusname;

    private String createtime;

    private String updatetime;

    private String paytime;

    private String paysucctime;

    public String getStatusname() {
        return statusname;
    }

    public void setStatusname(String statusname) {
        this.statusname = statusname;
    }

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

    public String getChannelname() {
        return channelname;
    }

    public void setChannelname(String channelname) {
        this.channelname = channelname;
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

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getPaytime() {
        return paytime;
    }

    public void setPaytime(String paytime) {
        this.paytime = paytime;
    }

    public String getPaysucctime() {
        return paysucctime;
    }

    public void setPaysucctime(String paysucctime) {
        this.paysucctime = paysucctime;
    }
}
