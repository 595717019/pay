package com.tre.bill.service.impl;

import com.tre.bill.domain.po.PayChannel;
import com.tre.bill.mapper.PayChannelMapper;
import com.tre.bill.service.IPayChannelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付渠道 服务实现类
 * </p>
 *
 * @author JDev
 * @since 2020-05-22
 */
@Service
public class PayChannelServiceImpl extends ServiceImpl<PayChannelMapper, PayChannel> implements IPayChannelService {

}
