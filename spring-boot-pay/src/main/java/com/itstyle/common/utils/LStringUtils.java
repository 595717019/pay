package com.itstyle.common.utils;

import jodd.util.StringUtil;

/**
 * @description: 共通字符处理
 * @author: JDev
 * @create: 2018-11-14 09:30
 **/
public class LStringUtils extends StringUtil {

    /**
     * 验证字符串数组中是否存在字符串
     *
     * @param arrStr
     * @param validateStr
     * @return
     */
    public static boolean containsByArray(String[] arrStr, String validateStr) {
        boolean flag = false;
        for (int i = 0; i < arrStr.length; i++) {
            String res = arrStr[i];
            if (res.equals(validateStr)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

}
