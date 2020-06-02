package com.itstyle.database.domain.vo;

import com.itstyle.database.domain.po.PayChannel;

import java.util.List;

/**
 * @Author 10097454
 * @Date 2020/05/15
 * @Description TODO
 */
public class StatusVO {
    private List<PayChannel> payChannels;//支付渠道

    public List<PayChannel> getPayChannels() {
        return payChannels;
    }

    public void setPayChannels(List<PayChannel> payChannels) {
        this.payChannels = payChannels;
    }
}
