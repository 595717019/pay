package com.itstyle.database.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itstyle.common.model.Product;
import com.itstyle.database.domain.dto.Channel;
import com.itstyle.database.domain.dto.HomeOrderDTO;
import com.itstyle.database.domain.po.MchInfo;
import com.itstyle.database.domain.po.PayChannel;
import com.itstyle.database.domain.po.PayOrder;
import com.itstyle.database.domain.dto.OrderDTO;
import com.itstyle.database.domain.vo.OrderVO;
import com.itstyle.database.domain.vo.StatusVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商户信息 Mapper 接口
 * </p>
 *
 * @author JDev
 * @since 2020-04-28
 */

public interface PayOrderMapper extends BaseMapper<MchInfo> {
    boolean insert(Product product);

    boolean update(Map<String, String> map);

    List<PayOrder> listAll();

    List<OrderDTO> getOrderList(OrderVO ov);//商户交易数据查询

    List<Channel> getChannelList();//获取支付通道列表

    boolean updateChannelStatus(StatusVO statusVO);//更新支付渠道状态

    List<OrderDTO> getOrderSum(OrderVO params);//首页显示日均笔数、交易金额

    boolean updateRecfee(Product product);//更新手续费和手续费费率

    List<HomeOrderDTO> getOrderRang(OrderVO params);//首页汇总

    boolean updateOrderStatus(Map<String, String> map);//更新订单状态
}
