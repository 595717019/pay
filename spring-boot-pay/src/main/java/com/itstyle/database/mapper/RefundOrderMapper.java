package com.itstyle.database.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itstyle.database.domain.po.RefundOrder;
import com.itstyle.database.domain.vo.RefundOrderVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 退款订单 Mapper 接口
 * </p>
 *
 * @author JDev
 * @since 2020-05-22
 */
public interface RefundOrderMapper extends BaseMapper<RefundOrder> {
    List<RefundOrder> selectRefundOrder(RefundOrderVO refundOrderVO);//查询数据库中的退款订单

    boolean insertRefund(RefundOrderVO refundOrderVO);//插入退款数据

    boolean updateRefund(Map<String, String> map);//更新
}
