package com.itstyle.common.exception;

/**
 * @description: 系统参数验证bean
 * @author: JDev
 * @create: 2018-12-03 14:01
 **/
public class SysExceptionValidator extends SysException {

    public SysExceptionValidator(String msg) {
        super(msg);
    }

    public SysExceptionValidator(String code, String msg) {
        super(code, msg);
    }
}
