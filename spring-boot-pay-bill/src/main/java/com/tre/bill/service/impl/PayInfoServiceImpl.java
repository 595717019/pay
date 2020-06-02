package com.tre.bill.service.impl;

import com.tre.bill.domain.po.PayInfo;
import com.tre.bill.mapper.PayInfoMapper;
import com.tre.bill.service.IPayInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付信息 服务实现类
 * </p>
 *
 * @author JDev
 * @since 2020-05-22
 */
@Service
public class PayInfoServiceImpl extends ServiceImpl<PayInfoMapper, PayInfo> implements IPayInfoService {

}
