package com.tre.bill.common.config;

import com.tre.bill.suixingpay.util.ConfigSuiXing;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 启动加载参数配置
 */
@Component
public class InitPay implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments var) {
        ConfigSuiXing.init("suixingpay.properties");//随行付
    }
}