package com.itstyle.modules.suixingpay.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.itstyle.modules.weixinpay.util.HttpUtil;
import com.itstyle.modules.weixinpay.util.PayCommonUtil;
import com.itstyle.modules.weixinpay.util.XMLUtil;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * 相关配置参数
 * 创建者 张永光
 */
public class ConfigSuiXing {
    private static Configuration configs;
    public static String Org_ID;
    public static String MNO;
    public static String SX_PRIVATE_KEY;
    public static String SX_PUBLIC_KEY;
    public static String SXP_PUBLIC_KEY;
    public static String SX_SIGN_TYPE;

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
        Org_ID = configs.getString("Org_ID");
        MNO = configs.getString("MNO");
        SX_PRIVATE_KEY = configs.getString("SX_PRIVATE_KEY");
        SX_PUBLIC_KEY = configs.getString("SX_PUBLIC_KEY");
        SXP_PUBLIC_KEY = configs.getString("SXP_PUBLIC_KEY");
        SX_SIGN_TYPE = configs.getString("SX_SIGN_TYPE");
    }

    /**
     * 基础接口地址
     */
    // 支付接口(POST)
    public final static String MICRO_URL = "https://openapi.tianquetech.com/order/reverseScan";
    // 支付统一接口(POST)
    public final static String UNIFIED_ORDER_URL = "https://openapi.tianquetech.com/order/activeScan";
    // 退款接口(POST)
    public final static String REFUND_URL = "https://openapi.tianquetech.com/order/refund";
    // 订单查询接口(POST)
    public final static String CHECK_ORDER_URL = "https://openapi.tianquetech.com/query/tradeQuery";
    // 手续费查询接口(POST)
    public final static String POUNDAGE_URL = "https://openapi.tianquetech.com/query/poundageQuery";
    // 退款查询接口(POST)
    public final static String REFUNDQUERY_URL = "https://openapi.tianquetech.com/query/refundQuery";

    /**
     * 基础参数
     *
     * @Author 张永光
     */
    public static void commonParams(ApiRequestBean<JSONObject> reqBean) {
        reqBean.setOrgId(Org_ID);
        reqBean.setReqId(UUID.randomUUID().toString().replaceAll("-", ""));
        reqBean.setSignType(SX_SIGN_TYPE);
        reqBean.setTimestamp(PayCommonUtil.getCurrTime());
        reqBean.setVersion("1.0");
    }

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
                type = "WECHAT";
                break;
            case 2:
                type = "ALIPAY";
                break;
            case 3:
                type = "UNIONPAY";
                break;
            default:
                type = "";
        }
        return type;
    }

    public static String sign(ApiRequestBean<JSONObject> reqBean) {
        String req = JSONObject.toJSONString(reqBean);
        HashMap reqMap = JSON.parseObject(req, LinkedHashMap.class, Feature.OrderedField);
        String signContent = RSASignature.getOrderContent(reqMap);
        String sign = RSASignature.encryptBASE64(RSASignature.sign(signContent, ConfigSuiXing.SX_PRIVATE_KEY));
        reqMap.put("sign", sign);
        String reqStr = JSON.toJSONString(reqMap);
        return reqStr;
    }
}