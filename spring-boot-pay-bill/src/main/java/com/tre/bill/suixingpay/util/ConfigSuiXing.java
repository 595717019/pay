package com.tre.bill.suixingpay.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.UUID;

/**
 * 相关配置参数
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

    //对账接口
    public final static String FILE_URL = "https://openapi.tianquetech.com/capital/fileDownload/getFileUrl";

    /**
     * 基础参数
     */
    public static void commonParams(ApiRequestBean<JSONObject> reqBean) {
        reqBean.setOrgId(Org_ID);
        reqBean.setReqId(UUID.randomUUID().toString().replaceAll("-", ""));
        reqBean.setSignType(SX_SIGN_TYPE);
        reqBean.setTimestamp(getCurrTime());
        reqBean.setVersion("1.0");
    }
    public static String getCurrTime() {
        Date now = new Date();
        SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String s = outFormat.format(now);
        return s;
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