package com.tre.code.auto.generator;

import com.tre.jdevtemplateboot.common.util.LJasyptUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * @description: 数据库密码加密
 * @author: JDev
 * @create: 2018-11-15 13:41
 **/
public class TreDBPasswordTool {

    private static final Logger logger = LoggerFactory.getLogger(TreDBPasswordTool.class);
    public static void main(String[] args) {
        logger.info("请输入要加密的字符：");
        Scanner scanner = new Scanner(System.in);
        String password = scanner.next();
        try {
            logger.info("加密后字符："+LJasyptUtils.encryptPwd(password));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
