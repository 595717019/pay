package com.itstyle.database.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itstyle.database.domain.po.MchInfo;
import com.itstyle.database.mapper.MchInfoMapper;
import com.itstyle.database.service.IPayOrderService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商户信息 服务实现类
 * </p>
 *
 * @author JDev
 * @since 2020-04-28
 */
@Service
public class PayOrderServiceImpl extends ServiceImpl<MchInfoMapper, MchInfo> implements IPayOrderService {

}
