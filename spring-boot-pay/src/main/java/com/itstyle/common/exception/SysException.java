package com.itstyle.common.exception;

/**
 * @description: JDev自定义系统异常
 * @author: JDev
 * @create: 2018-11-22 09:58
 **/
public class SysException extends RuntimeException {

    private String code;
    private String msg;

    public SysException(String msg) {
        super(msg);
    }

    public SysException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
