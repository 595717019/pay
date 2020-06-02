package com.tre.bill.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.tre.bill.common.util.HttpDownload;
import com.tre.bill.suixingpay.util.ApiRequestBean;
import com.tre.bill.suixingpay.util.ConfigSuiXing;
import com.tre.bill.suixingpay.util.HttpUtils;
import com.tre.jdevtemplateboot.common.dto.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @Author 10097454
 * @Date 2020/05/20
 * @Description 对账单相关操作
 */
@Api(tags = "对账单下载")
@Controller
@RequestMapping("/download")
public class DownloadController {
    private static final Logger logger = LoggerFactory.getLogger(DownloadController.class);

    //获取文件上传路径
    @Value("${spring.servlet.multipart.location}")
    private String fileSavePath;

    //文件名
    @Value("${spring.servlet.fileName}")
    private String fileName;

    @ApiOperation(value = "获取对账单下载地址")
    @RequestMapping(value = "getDownloadUrl", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult getDownloadUrl(String billDate, String billType) {
        logger.info("获取对账单下载地址");
        String downloadUrl = "";
//        String billDate = "20200521";
//        String billType = "00";//对账单类型（00 交易 01 结算 02 分账）
        if (StringUtils.isBlank(billDate)) {
            return ResponseResult.buildOK("对账单日期不能为空");//对账单日期不能为空
        }
        if (StringUtils.isBlank(billType)) {
            return ResponseResult.buildOK("对账单类型不能为空(00 交易 01 结算 02 分账)");//对账单类型不能为空(00 交易 01 结算 02 分账)
        }
        ApiRequestBean<JSONObject> reqBean = new ApiRequestBean<JSONObject>();
        JSONObject reqData = new JSONObject();
        //业务参数
        reqData.put("billDate", billDate);
        reqData.put("billType", billType);
        reqBean.setReqData(reqData);
        //封装通用参数
        ConfigSuiXing.commonParams(reqBean);
        String reqStr = ConfigSuiXing.sign(reqBean);

        System.out.println("请求参数:" + reqStr);
        String resultJson = HttpUtils.connectPostUrl(ConfigSuiXing.FILE_URL, reqStr);
        System.out.println("返回信息：" + resultJson);
//        HttpRequest.downLoadFromUrl(url,"abc.xls","D:\\",token);
        //不要对reqData排序 所以用LinkedHashMap
        HashMap<String, Object> result = JSON.parseObject(resultJson, LinkedHashMap.class, Feature.OrderedField);
        if (result.get("respData") != null) {
            JSONObject resp = JSON.parseObject(result.get("respData").toString());
            if ("0000".equals(result.get("code")) && resp.getString("bizCode").equals("00")) {
                downloadUrl = resp.getString("downloadUrl");
            }
        }
        logger.info("对账单下载路径：{}",downloadUrl);
        return ResponseResult.buildOK(downloadUrl);
    }

    @ApiOperation(value = "下载对账单文件")
    @RequestMapping(value = "downloadFile", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult downloadFile(String httpUrl) {
        logger.info("下载对账单文件开始");
        if (StringUtils.isBlank(httpUrl)) {
            return ResponseResult.buildOK("请输入正确的账单下载地址");
        }
        logger.info("JOB下载对账单文件保存路径：{}/{}",fileSavePath,fileName);
        boolean isOK = HttpDownload.download(httpUrl, fileName, fileSavePath);
        logger.info("下载对账单文件{}",isOK?"成功":"失败");
        return ResponseResult.buildOK(isOK ? "对账单下载成功" : "对账单下载失败");
    }

    @ApiOperation(value = "JOB下载对账单文件")
    @RequestMapping(value = "jobDownloadFile", method = RequestMethod.POST)
    @ResponseBody
    public int jobDownloadFile() {
        logger.info("JOB下载对账单文件开始");
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
        String billDate = df.format(new Date());//对账单日期
        String billType = "00";//对账单类型（00 交易 01 结算 02 分账）
        logger.info("billDate:{},billType:{}", billDate, billType);

        ResponseResult result = getDownloadUrl(billDate, billType);
        String httpUrl = result.getData().toString();
        ResponseResult resultDownload = downloadFile(httpUrl);
        int code = resultDownload.getCode();
        logger.info("JOB下载对账单文件结束:{}",code);
        return code;
    }
}
