package com.itstyle.common.exception;

/**
 * @description:
 * @author: JDev
 * @create: 2019-03-27 15:59
 **/
public class SystemExceptionAuthorization extends SysException {
    public SystemExceptionAuthorization(String msg) {
        super(msg);
    }

    public SystemExceptionAuthorization(String code, String msg) {
        super(code, msg);
    }
}
