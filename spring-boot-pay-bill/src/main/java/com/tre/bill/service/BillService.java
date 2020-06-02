package com.tre.bill.service;

import com.tre.bill.domain.dto.BillInfo;
import org.springframework.stereotype.Component;
//import com.alibaba.dubbo.config.annotation.Service;

import java.util.List;

/**
 * @Author 10097454
 * @Date 2020/05/22
 * @Description TODO
 */
//@Service
@Component
public interface BillService {
    /**
     * @param
     * @return {@link List <  BillInfo >}
     * @Author 10097454
     * @Date 2020/05/22
     * @Description 掌柜智囊，支付平台，随行付数据
     **/
    List<BillInfo> getBillData(String mchid, String starttime, String endtime);
}
