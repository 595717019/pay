package com.itstyle.modules.control;

import com.itstyle.common.enums.ResultEnum;
import com.itstyle.common.model.ResponseResult;
import com.itstyle.common.utils.DateUtil;
import com.itstyle.database.domain.dto.HomeOrderDTO;
import com.itstyle.database.domain.po.PayOrder;
import com.itstyle.database.mapper.PayOrderMapper;
import com.itstyle.database.domain.dto.OrderDTO;
import com.itstyle.database.domain.vo.OrderVO;
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

import java.util.*;

@Api(tags = "订单")
@Controller
@RequestMapping(value = "order")
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private PayOrderMapper payOrderMapper;

    @ApiOperation(value = "订单列表")
    @RequestMapping(value = "orderlist", method = RequestMethod.POST)
    public @ResponseBody
    List<PayOrder> orderlist() {
        List<PayOrder> list = payOrderMapper.listAll();
        return list;
    }

    @ApiOperation(value = "订单检索")
    @RequestMapping(value = "getOrderList", method = RequestMethod.POST)
    public @ResponseBody
    ResponseResult getOrderList(OrderVO params) {
        logger.info("订单检索");
        OrderVO ov = new OrderVO();
        ov.setMchid(params.getMchid());//商户编号
        ov.setMchname(params.getMchname());//签购单名称
        ov.setPayid(params.getPayid());//支付渠道
        ov.setOuttradeno(params.getOuttradeno());//交易流水号
        ov.setStatus(params.getStatus());//交易状态
        ov.setPaytype(params.getPaytype());//交易类型
        ov.setStarttime(params.getStarttime());//交易开始日期
        ov.setEndtime(params.getEndtime());//交易结束日期
        List<OrderDTO> list = payOrderMapper.getOrderList(params);
        return ResponseResult.buildOK(list);
    }

    @ApiOperation(value = "首页显示日均笔数、交易金额")
    @RequestMapping(value = "getOrderSum", method = RequestMethod.POST)
    public @ResponseBody
    ResponseResult getOrderSum(OrderVO params) {
        logger.info("首页显示日均笔数、交易金额");
        String starttime = params.getStarttime();
        String endtime = params.getEndtime();
        if (StringUtils.isBlank(starttime) || StringUtils.isBlank(endtime)) {
            return ResponseResult.buildError(ResultEnum.Date_NOT_NULL);//开始日期和结束日期不能为空
        }
        if (DateUtil.compareDate(starttime, endtime)) {
            return ResponseResult.buildError(ResultEnum.Date_COMPARE);//开始日期必须小于结束日期
        }
        if (starttime.length() != 10) {
            params.setStarttime(DateUtil.fomatDateString(starttime));
        }
        if (endtime.length() != 10) {
            params.setEndtime(DateUtil.fomatDateString(endtime));
        }
        List<OrderDTO> list = payOrderMapper.getOrderSum(params);
        return ResponseResult.buildOK(list);
    }

    @ApiOperation(value = "首页汇总")
    @RequestMapping(value = "getOrderRang", method = RequestMethod.POST)
    public @ResponseBody ResponseResult getOrderRang(OrderVO params) {
        logger.info("首页汇总");
        List<HomeOrderDTO> list = payOrderMapper.getOrderRang(params);
        return ResponseResult.buildOK(list);
    }

    @ApiOperation(value = "更新订单状态")
    @RequestMapping(value = "updateOrderStatus", method = RequestMethod.POST)
    @ResponseBody
    public boolean updateOrderStatus(String outTradeNo, String status) {
        logger.info("更新订单状态开始 订单号：{}\t状态：{}", outTradeNo, status);
        boolean isUpdate = false;
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("outTradeNo",outTradeNo);
            params.put("status",status);
            isUpdate = payOrderMapper.updateOrderStatus(params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            logger.info("订单{} 更新{}状态{}", outTradeNo, status, isUpdate ? "成功" : "失败");
            return isUpdate;
        }
    }
}
