package com.tre.bill.service.impl;

import com.tre.bill.domain.po.PayOrder;
import com.tre.bill.mapper.PayOrderMapper;
import com.tre.bill.service.IPayOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JDev
 * @since 2020-05-22
 */
@Service
public class PayOrderServiceImpl extends ServiceImpl<PayOrderMapper, PayOrder> implements IPayOrderService {

}
