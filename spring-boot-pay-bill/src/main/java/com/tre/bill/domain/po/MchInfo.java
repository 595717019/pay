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
public class MchInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商户ID
     */
    private String mchid;

    /**
     * 商户名称
     */
    private String mchname;

    /**
     * 商户联系人
     */
    private String username;

    /**
     * 商户等级
     */
    private Integer mchlevel;

    /**
     * 业务员
     */
    private String operater;

    /**
     * 商户状态,0-停止使用,1-使用中
     */
    private Integer state;

    /**
     * 注册商户名称
     */
    private String registermchname;

    /**
     * 注册登记号
     */
    private String registerno;

    /**
     * 法人代表姓名
     */
    private String registerusername;

    /**
     * 营业执照期限
     */
    private String registerperiod;

    /**
     * 法人证件号码
     */
    private String registeridcard;

    /**
     * 法人证件类型
     */
    private String registeridcardtype;

    /**
     * 商户联系人电话
     */
    private String phone;

    /**
     * 法人证件期限
     */
    private String registeridcardperiod;

    /**
     * 经营地址
     */
    private String mchaddress;

    /**
     * 商户类别
     */
    private String mchtype;

    /**
     * 结算人身份证号
     */
    private String settlementidcard;

    /**
     * 结算人姓名
     */
    private String settlementusername;

    /**
     * 银行信息
     */
    private String settlementaddress;

    private String settlementtype;

    /**
     * 结算账号
     */
    private String settlementcardno;

    /**
     * 结算天数
     */
    private String settlementperiod;

    private Date createtime;

    private Date updatetime;

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
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public Integer getMchlevel() {
        return mchlevel;
    }

    public void setMchlevel(Integer mchlevel) {
        this.mchlevel = mchlevel;
    }
    public String getOperater() {
        return operater;
    }

    public void setOperater(String operater) {
        this.operater = operater;
    }
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
    public String getRegistermchname() {
        return registermchname;
    }

    public void setRegistermchname(String registermchname) {
        this.registermchname = registermchname;
    }
    public String getRegisterno() {
        return registerno;
    }

    public void setRegisterno(String registerno) {
        this.registerno = registerno;
    }
    public String getRegisterusername() {
        return registerusername;
    }

    public void setRegisterusername(String registerusername) {
        this.registerusername = registerusername;
    }
    public String getRegisterperiod() {
        return registerperiod;
    }

    public void setRegisterperiod(String registerperiod) {
        this.registerperiod = registerperiod;
    }
    public String getRegisteridcard() {
        return registeridcard;
    }

    public void setRegisteridcard(String registeridcard) {
        this.registeridcard = registeridcard;
    }
    public String getRegisteridcardtype() {
        return registeridcardtype;
    }

    public void setRegisteridcardtype(String registeridcardtype) {
        this.registeridcardtype = registeridcardtype;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getRegisteridcardperiod() {
        return registeridcardperiod;
    }

    public void setRegisteridcardperiod(String registeridcardperiod) {
        this.registeridcardperiod = registeridcardperiod;
    }
    public String getMchaddress() {
        return mchaddress;
    }

    public void setMchaddress(String mchaddress) {
        this.mchaddress = mchaddress;
    }
    public String getMchtype() {
        return mchtype;
    }

    public void setMchtype(String mchtype) {
        this.mchtype = mchtype;
    }
    public String getSettlementidcard() {
        return settlementidcard;
    }

    public void setSettlementidcard(String settlementidcard) {
        this.settlementidcard = settlementidcard;
    }
    public String getSettlementusername() {
        return settlementusername;
    }

    public void setSettlementusername(String settlementusername) {
        this.settlementusername = settlementusername;
    }
    public String getSettlementaddress() {
        return settlementaddress;
    }

    public void setSettlementaddress(String settlementaddress) {
        this.settlementaddress = settlementaddress;
    }
    public String getSettlementtype() {
        return settlementtype;
    }

    public void setSettlementtype(String settlementtype) {
        this.settlementtype = settlementtype;
    }
    public String getSettlementcardno() {
        return settlementcardno;
    }

    public void setSettlementcardno(String settlementcardno) {
        this.settlementcardno = settlementcardno;
    }
    public String getSettlementperiod() {
        return settlementperiod;
    }

    public void setSettlementperiod(String settlementperiod) {
        this.settlementperiod = settlementperiod;
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
        return "MchInfo{" +
        "mchid=" + mchid +
        ", mchname=" + mchname +
        ", username=" + username +
        ", mchlevel=" + mchlevel +
        ", operater=" + operater +
        ", state=" + state +
        ", registermchname=" + registermchname +
        ", registerno=" + registerno +
        ", registerusername=" + registerusername +
        ", registerperiod=" + registerperiod +
        ", registeridcard=" + registeridcard +
        ", registeridcardtype=" + registeridcardtype +
        ", phone=" + phone +
        ", registeridcardperiod=" + registeridcardperiod +
        ", mchaddress=" + mchaddress +
        ", mchtype=" + mchtype +
        ", settlementidcard=" + settlementidcard +
        ", settlementusername=" + settlementusername +
        ", settlementaddress=" + settlementaddress +
        ", settlementtype=" + settlementtype +
        ", settlementcardno=" + settlementcardno +
        ", settlementperiod=" + settlementperiod +
        ", createtime=" + createtime +
        ", updatetime=" + updatetime +
        "}";
    }
}
