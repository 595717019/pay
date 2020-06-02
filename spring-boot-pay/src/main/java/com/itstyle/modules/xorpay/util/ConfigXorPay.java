package com.itstyle.modules.xorpay.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.itstyle.modules.suixingpay.util.ApiRequestBean;
import com.itstyle.modules.suixingpay.util.RSASignature;
import com.itstyle.modules.weixinpay.util.PayCommonUtil;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.UUID;

/**
 * 相关配置参数
 * 创建者 张永光
 */
public class ConfigXorPay {
    private static Configuration configs;

    public static synchronized void init(String filePath) {
        if (configs != null) {
            return;
        }
        try {
            configs = new PropertiesConfiguration(filePath);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }

        if (configs == null) {
            throw new IllegalStateException("can`t find file by path:"
                    + filePath);
        }
    }

    /**
     * 基础接口地址
     */
    // 支付接口(POST)
    public final static String MICRO_URL = "https://pos.xorpay.com/api/barcode_pay/";
    // 支付统一接口(POST)
    public final static String UNIFIED_ORDER_URL = "https://pos.xorpay.com/api/pay/";
    // 退款接口(POST)
    public final static String REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
    // 订单查询接口(POST)
    public final static String CHECK_ORDER_URL = "https://pos.xorpay.com/api/query2/";

    /**
     * payType 数字转字符串
     *
     * @param payType
     * @return
     */
    public static String payType(Short payType) {
        String type;
        switch (payType) {
            case 1:
                type = "native";
                break;
            case 2:
                type = "alipay";
                break;
            default:
                type = "";
        }
        return type;
    }

    public static String payTradeType(Short payType) {
        String type;
        switch (payType) {
            case 1:
                type = "wechat_barcode";
                break;
            case 2:
                type = "alipay_barcode";
                break;
            default:
                type = "";
        }
        return type;
    }
}