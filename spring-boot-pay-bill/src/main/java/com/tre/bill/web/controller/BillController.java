package com.tre.bill.web.controller;

import com.tre.bill.common.util.LDateUtils;
import com.tre.bill.domain.dto.BillInfo;
import com.tre.bill.domain.dto.ExceptionBillData;
import com.tre.bill.domain.po.Bill;
import com.tre.bill.domain.vo.BillVO;
import com.tre.bill.mapper.BillMapper;
import com.tre.bill.service.BillService;
import com.tre.jdevtemplateboot.common.dto.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author 10097454
 * @Date 2020/05/22
 * @Description TODO
 */
@Api(tags = "对账单数据")
@Controller
@RequestMapping("/data")
public class BillController {
    private static final Logger logger = LoggerFactory.getLogger(BillController.class);

    @Autowired
    BillService service;

    @Autowired
    BillMapper mapper;

    @ApiOperation(value = "掌柜智囊，支付平台，随行付数据查询")
    @RequestMapping(value = "getBillData", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult getBillData(String mchid, String starttime, String endtime) {
        logger.info("掌柜智囊，支付平台，随行付数据查询开始");

        if (StringUtils.isBlank(starttime) || StringUtils.isBlank(endtime)) {
            return ResponseResult.buildOK("开始日期和结束日期不能为空");//开始日期和结束日期不能为空
        }
        if (LDateUtils.compareDate(starttime, endtime)) {
            return ResponseResult.buildOK("开始日期必须小于结束日期");//开始日期必须小于结束日期
        }
        if (starttime.length() != 10) {
            starttime = LDateUtils.fomatDateString(starttime);
        }
        if (endtime.length() != 10) {
            endtime = LDateUtils.fomatDateString(endtime);
        }
        logger.info("掌柜智囊，支付平台，随行付数据查询参数:mchid:{};starttime:{};endtime:{}", mchid, starttime, endtime);
        List<BillInfo> list = mapper.getBillData(mchid, starttime, endtime);
        logger.info("掌柜智囊，支付平台，随行付数据查询结束：数量：{}", list.size());
        return ResponseResult.buildOK(list);
    }

    @ApiOperation(value = "异常数据查询")
    @RequestMapping(value = "getExceptionBillData", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult getExceptionBillData(@RequestBody BillVO billVO) {
        logger.info("异常数据查询开始");
        String mchid = billVO.getMchid();
        String starttime = billVO.getStarttime();
        String endtime = billVO.getEndtime();
        String source = billVO.getSource();

        if (StringUtils.isBlank(starttime) || StringUtils.isBlank(endtime)) {
            return ResponseResult.buildOK("开始日期和结束日期不能为空");//开始日期和结束日期不能为空
        }
        if (LDateUtils.compareDate(starttime, endtime)) {
            return ResponseResult.buildOK("开始日期必须小于结束日期");//开始日期必须小于结束日期
        }
        if (starttime.length() != 10) {
            starttime = LDateUtils.fomatDateString(starttime);
        }
        if (endtime.length() != 10) {
            endtime = LDateUtils.fomatDateString(endtime);
        }
        logger.info("异常数据查询参数:商户ID:{};开始时间:{};结束时间:{}；异常类型:{}", mchid, starttime, endtime, source);
        List<ExceptionBillData> list = mapper.getExceptionBillData(mchid, starttime, endtime, source);
        logger.info("异常数据查询结束：数量：{}", list.size());
        return ResponseResult.buildOK(list);
    }

    @ApiOperation(value = "异常数据修改")
    @RequestMapping(value = "updateExceptionBillData", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult updateExceptionBillData(@RequestBody BillVO billVO) {
        logger.info("异常数据修改开始");
        String mchid = billVO.getMchid();
        String outtradeno = billVO.getOuttradeno();
        String paytime = billVO.getPaytime();
        String remark = billVO.getRemark();

        if (StringUtils.isBlank(outtradeno)) {
            return ResponseResult.buildOK("流水号不能为空");
        }
        if (StringUtils.isBlank(paytime)) {
            return ResponseResult.buildOK("交易时间不能为空");
        }

        logger.info("异常数据修改参数:商户ID:{};流水号:{}；交易时间:{}；备注:{}", mchid, outtradeno, paytime, remark);
        boolean isUpdate = mapper.updateExceptionBillData(billVO);
        logger.info("异常数据修改：{}", isUpdate ? "成功" : "失败");
        return ResponseResult.buildOK(isUpdate);
    }
}
