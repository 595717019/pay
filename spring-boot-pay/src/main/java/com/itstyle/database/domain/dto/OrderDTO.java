package com.itstyle.database.domain.dto;

/**
 * @Author 10097454
 * @Date 2020/05/07
 * @Description TODO
 */
public class OrderDTO {
    private String mchid;//商户编号
    private String mchname;//商户编号
    private String payid;//支付渠道
    private String outtradeno;//交易流水号
    private String status;//交易状态
    private String statusname;//交易状态
    private String channelid;
    private Integer totalfee;
    private String paytype;//交易类型
    private String payname;//交易类型
    private String paytypename;
    private String createtime;//交易日期
    private Double recfeeamt;//交易手续费:单位元
    private Double recfeerate;//交易手续费率:单位%

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

    public String getPayid() {
        return payid;
    }

    public void setPayid(String payid) {
        this.payid = payid;
    }

    public String getOuttradeno() {
        return outtradeno;
    }

    public void setOuttradeno(String outtradeno) {
        this.outtradeno = outtradeno;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusname() {
        return statusname;
    }

    public void setStatusname(String statusname) {
        this.statusname = statusname;
    }

    public String getChannelid() {
        return channelid;
    }

    public void setChannelid(String channelid) {
        this.channelid = channelid;
    }

    public Integer getTotalfee() {
        return totalfee;
    }

    public void setTotalfee(Integer totalfee) {
        this.totalfee = totalfee;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

    public String getPayname() {
        return payname;
    }

    public void setPayname(String payname) {
        this.payname = payname;
    }

    public String getPaytypename() {
        return paytypename;
    }

    public void setPaytypename(String paytypename) {
        this.paytypename = paytypename;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
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
}
