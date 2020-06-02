package com.itstyle.modules.service.impl;

import com.itstyle.common.model.Product;
import com.itstyle.modules.service.IOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class OrderService implements IOrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);


    @Override
    public Boolean insert(Product product) {

        return null;
    }
}
