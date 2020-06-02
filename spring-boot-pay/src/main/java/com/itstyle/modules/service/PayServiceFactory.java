package com.itstyle.modules.service;

import com.itstyle.common.model.Product;
import com.itstyle.modules.service.impl.AlipayService;
import com.itstyle.modules.service.impl.SuixingpayService;
import com.itstyle.modules.service.impl.WechatService;
import com.itstyle.modules.service.impl.XorpayService;

public class PayServiceFactory {

    public IPayService create(Product product) {
        switch (product.getPayType()) {
            case "alipay":
                return new AlipayService();
            case "wechat":
                return new WechatService();
            case "xorpay":
                return new XorpayService();
            case "suixingpay":
                return new SuixingpayService();
        }
        return null;
    }
}
