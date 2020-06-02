package com.itstyle.database.domain.po;

import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 支付信息
 * </p>
 *
 * @author JDev
 * @since 2020-05-07
 */
public class PayInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 支付ID
     */
    private Integer payid;

    /**
     * 支付名称
     */
    private String name;

    /**
     * 支付状态,0-停止使用,1-使用中
     */
    private Integer state;

    private Date createtime;

    private Date updatetime;

    private Integer paytype;

    private String descript;

    public Integer getPayid() {
        return payid;
    }

    public void setPayid(Integer payid) {
        this.payid = payid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getPaytype() {
        return paytype;
    }

    public void setPaytype(Integer paytype) {
        this.paytype = paytype;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    @Override
    public String toString() {
        return "PayInfo{" +
                "payid=" + payid +
                ", name=" + name +
                ", state=" + state +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                ", paytype=" + paytype +
                ", descript=" + descript +
                "}";
    }
}
