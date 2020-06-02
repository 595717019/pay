package com.itstyle.common.constants;

/**
 * @description: 系统常量定义
 * @author: JDev
 * @create: 2018-11-13 14:12
 **/
public class SysConstantInfo {

    public static String SERVER_SUCCESS_CODE = "0";
    public static String SERVER_SUCCESS_MSG = "SERVER_SUCCESS";

    public static String SERVER_ERROR_CODE = "1";
    public static String SERVER_ERROR_MSG = "服务器端错误发生!";

    public static String SERVER_DUPLICATEKEY_CODE = "2";
    public static String SERVER_DUPLICATEKEY_MSG = "主键冲突!";

    public static String SERVER_VALIDATE_CODE = "3";
    public static String SERVER_VALIDATE_MSG = "参数校验错误!";

    public static String SERVER_REQUEST_RESULT_CODE = "3";
    public static String SERVER_REQUEST_RESULT_MSG = "请求失败！";

    public static String SERVER_REQUEST_AUTHENTICATION_CODE = "4";
    public static String SERVER_REQUEST_AUTHENTICATION_MSG = "认证token不合法，请登录！";

    public static String SERVER_REQUEST_AUTHORIZATION_CODE = "5";
    public static String SERVER_REQUEST_AUTHORIZATION_MSG = "该用户没有被授权访问资源";

    public static String SERVER_REQUEST_TOKEN_EXPIRED_CODE = "6";
    public static String SERVER_REQUEST_TOKEN_EXPIRED_MSG = "认证token已过期，请重新获取！";

    public static String SERVER_REQUEST_TOKEN_INVALID_CODE = "7";
    public static String SERVER_REQUEST_TOKEN_INVALID_MSG = "认证token无效！";

    public static String SERVER_REQUEST_TOKEN_ERROR_CODE = "8";
    public static String SERVER_REQUEST_TOKEN_ERROR_MSG = "认证token无效！";
}
