package com.tre.bill.service.impl;

import com.tre.bill.domain.dto.BillInfo;
import com.tre.bill.mapper.BillMapper;
import com.tre.bill.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author 10097454
 * @Date 2020/05/22
 * @Description TODO
 */
public class BillImpl implements BillService {
    @Autowired
    BillMapper billMapper;

    @Override
    public List<BillInfo> getBillData(String mchid, String starttime, String endtime) {
        return billMapper.getBillData(mchid, starttime, endtime);
    }
}
