package com.tre.bill.service.impl;

import com.tre.bill.domain.po.RefundOrder;
import com.tre.bill.mapper.RefundOrderMapper;
import com.tre.bill.service.IRefundOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 退款订单 服务实现类
 * </p>
 *
 * @author JDev
 * @since 2020-05-22
 */
@Service
public class RefundOrderServiceImpl extends ServiceImpl<RefundOrderMapper, RefundOrder> implements IRefundOrderService {

}
