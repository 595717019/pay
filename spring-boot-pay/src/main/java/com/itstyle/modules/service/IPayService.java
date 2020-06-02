package com.itstyle.modules.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.itstyle.common.model.Product;
import org.springframework.stereotype.Component;

@Service   //属于Dubbo的@Service注解，非Spring  作用：暴露服务
@Component
public interface IPayService {
    /**
     * 扫码付
     *
     * @param product
     * @return
     * @Author 张永光
     */
    String pay(Product product);

    /**
     * 条码付
     *
     * @param product
     * @return
     * @Author 张永光
     */
    String tradePay(Product product);

    /**
     * 订单查询
     *
     * @param product
     * @return
     * @Author 张永光
     */
    String orderquery(Product product);

    /**
     * 支付退款
     *
     * @param product
     * @return String
     * @Author 张永光
     */
    String refund(Product product,String ordNo);

    /**
     * 设置子商户号
     *
     * @param product
     * @Author 张永光
     */
    void setSubMchId(Product product);

    /**
     * 设置支付通道
     *
     * @param product
     * @Author 张永光
     */
    short getPayType(Product product);

    /**
     * @Author 10097454
     * @Date 2020/05/15
     * @Description 手续费查询接口
     **/
    JSONObject poundage(String mno, String ordNo);

    /**
     * @Author 10097454 
     * @Date 2020/05/28
     * @Description 退款查询接口
     **/
    String refundQuery(Product product);
}
