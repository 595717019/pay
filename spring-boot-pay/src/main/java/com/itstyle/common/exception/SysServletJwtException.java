package com.itstyle.common.exception;

import javax.servlet.ServletException;

/**
 * @description:
 * @author: JDev
 * @create: 2019-03-28 10:35
 **/
public class SysServletJwtException extends ServletException {

    private String code;
    private String msg;

    public SysServletJwtException(String msg) {
        super(msg);
    }

    public SysServletJwtException(String code, String msg) {
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
