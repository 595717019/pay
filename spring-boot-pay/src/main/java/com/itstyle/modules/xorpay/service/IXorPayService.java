package com.itstyle.modules.xorpay.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.itstyle.common.model.Product;

/**
 * 扫码支付以及手机H5支付
 * 创建者 张永光
 */
@Service
public interface IXorPayService {
    /**
     * 交易主扫(C扫B)
     *
     * @param product
     * @return String
     * @Author 张永光
     */
    String xorPay(Product product);

    /**
     * 交易被扫(B扫C)
     *
     * @param product
     * @return String
     * @Auther 张永光
     */
    String xorTradePay(Product product);

    /**
     * 交易退款
     *
     * @param product
     * @return String
     * @Author 张永光
     */
    String xorRefund(Product product);

    /**
     * 交易查询
     *
     * @param product
     * @Author 张永光
     */
    String orderquery(Product product);
}
