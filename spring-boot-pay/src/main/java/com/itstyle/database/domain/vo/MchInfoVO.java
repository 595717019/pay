package com.itstyle.database.domain.vo;

/**
 * @Author 10097454
 * @Date 2020/05/11
 * @Description TODO
 */
public class MchInfoVO {
    /**
     * 商户编码
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
    private String mchlevel;

    /**
     * 业务员
     */
    private String operater;

    /**
     * 商户状态,0-停止使用,1-使用中
     */
    private Integer state;
    private String starttime;//交易开始日期
    private String endtime;//交易结束日期

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

    public String getMchlevel() {
        return mchlevel;
    }

    public void setMchlevel(String mchlevel) {
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

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
}
