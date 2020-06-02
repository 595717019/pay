package com.itstyle.modules.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.itstyle.common.model.Product;
import org.springframework.stereotype.Component;

@Service   //属于Dubbo的@Service注解，非Spring  作用：暴露服务
@Component
public interface IOrderService {
    /**
     * 记录支付订单
     *
     * @param product
     * @return
     * @Author 张永光
     */
    Boolean insert(Product product);

}
