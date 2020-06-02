package com.itstyle.database.mapper;

import com.itstyle.common.model.Product;
import com.itstyle.database.domain.dto.DropDownList;
import com.itstyle.database.domain.po.MchInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itstyle.database.domain.dto.MachinfoDTO;
import com.itstyle.database.domain.po.RefundOrder;
import com.itstyle.database.domain.vo.MchInfoVO;
import com.itstyle.database.domain.vo.StatusVO;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
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

public interface MchInfoMapper extends BaseMapper<MchInfo> {
    List<MchInfo> listAll();

    HashMap<String, Object> payinfo(Product product);

    List<MchInfo> getMchInfoList(MchInfoVO mchInfoVO);//商户列表

    MachinfoDTO getStoreDetail(String mchid);//商户详情

    HashMap<String, Object> orderRoute(Product product);//获取支付订单路由

    List<DropDownList> getMchList();//获取商户下拉列表

    boolean insertMch(MchInfo mchInfo);//新增商户

    HashMap<String, Object> refundOrderRoute(Product product);//获取退款订单路由
}
