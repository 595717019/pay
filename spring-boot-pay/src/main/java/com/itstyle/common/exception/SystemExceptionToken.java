package com.itstyle.common.exception;

/**
 * @description:
 * @author: JDev
 * @create: 2019-03-27 09:57
 **/
public class SystemExceptionToken extends SysException {

    public SystemExceptionToken(String msg) {
        super(msg);
    }

    public SystemExceptionToken(String code, String msg) {
        super(code, msg);
    }
}
