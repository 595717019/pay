package com.itstyle.modules.unionpay.service;

import java.util.Map;

import com.alibaba.dubbo.config.annotation.Service;
import com.itstyle.common.model.Product;

@Service
public interface IUnionPayService {
    /**
     * 银联支付
     *
     * @param product
     * @return String
     * @Author 科帮网
     * @Date 2017年8月2日 更新日志
     * 2017年8月2日  科帮网 首次创建
     */
    String unionPay(Product product);

    /**
     * 前台回调验证
     *
     * @param valideData
     * @param encoding
     * @return String
     * @Author 科帮网
     * @Date 2017年8月2日 更新日志
     * 2017年8月2日  科帮网 首次创建
     */
    String validate(Map<String, String> valideData, String encoding);

    /**
     * 对账单下载
     *
     * @Author 科帮网  void
     * @Date 2017年8月2日 更新日志
     * 2017年8月2日  科帮网 首次创建
     */
    void fileTransfer();
}
