package com.tre.bill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tre.bill.domain.dto.BillInfo;
import com.tre.bill.domain.dto.ExceptionBillData;
import com.tre.bill.domain.po.Bill;
import com.tre.bill.domain.vo.BillVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author 10097454
 * @Date 2020/05/22
 * @Description TODO
 */
@Mapper
public interface BillMapper {
    /**
     * @param
     * @return {@link List< BillInfo>}
     * @Author 10097454
     * @Date 2020/05/22
     * @Description 掌柜智囊，支付平台，随行付数据
     **/
    List<BillInfo> getBillData(String mchid, String starttime, String endtime);

    /**
     * @Author 10097454
     * @Date 2020/05/25
     * @Description 异常数据
     * @param mchid
     * @param starttime
     * @param endtime
     * @param source
     * @return {@link List< BillInfo>}
     **/
    List<ExceptionBillData> getExceptionBillData(String mchid, String starttime, String endtime, String source);

    /**
     * @Author 10097454
     * @Date 2020/05/26
     * @Description 异常数据修改
     * @return {@link boolean}
     **/
    boolean updateExceptionBillData(BillVO billVO);
}
