package com.itstyle.common.enums;

/**
 * @description:
 * @author: JDev
 * @create: 2019-03-27 08:59
 **/
public enum ResultEnum {

    Mchid_NOT_EXISTS("1001", "请输入商户编号"),
    Date_NOT_NULL("1002", "开始日期和结束日期不能为空"),
    Date_COMPARE("1003", "开始日期必须小于结束日期"),
    ;


    private String code;
    private String msg;


    ResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResultEnum stateOf(String code) {
        for (ResultEnum state : values()) {
            if (state.getCode() == code) {
                return state;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
