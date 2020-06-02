package com.itstyle.modules.control;

import com.itstyle.common.enums.ResultEnum;
import com.itstyle.common.model.ResponseResult;
import com.itstyle.database.domain.dto.DropDownList;
import com.itstyle.database.domain.po.MchInfo;
import com.itstyle.database.domain.vo.MchInfoVO;
import com.itstyle.database.domain.vo.StatusVO;
import com.itstyle.database.mapper.MchInfoMapper;
import com.itstyle.database.domain.dto.MachinfoDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @Author 10097454
 * @Date 2020/05/07
 * @Description 商户
 */
@Api(tags = "商户")
@Controller
@RequestMapping(value = "store")
public class MchinfoController {
    private static final Logger logger = LoggerFactory.getLogger(MchinfoController.class);

    @Autowired
    private MchInfoMapper mchInfoMapper;

    @ApiOperation(value = "商户列表")
    @RequestMapping(value = "getAllStore", method = RequestMethod.POST)
    public @ResponseBody
    ResponseResult getAllStore(MchInfoVO mchInfoVO) {
        logger.info("商户列表");
        List<MchInfo> mchInfo = mchInfoMapper.getMchInfoList(mchInfoVO);
        return ResponseResult.buildOK(mchInfo);
    }

    @ApiOperation(value = "商户详情")
    @RequestMapping(value = "getStoreDetail", method = RequestMethod.POST)
    public @ResponseBody
    ResponseResult getStoreDetail(String mchid) {
        logger.info("商户详情");
        if (StringUtils.isBlank(mchid)) {
            return ResponseResult.buildError(ResultEnum.Mchid_NOT_EXISTS);//请输入商户编号
        }
        MachinfoDTO mchInfo = mchInfoMapper.getStoreDetail(mchid);
        return ResponseResult.buildOK(mchInfo);
    }

    @ApiOperation(value = "获取商户下拉列表")
    @RequestMapping(value = "getMchList", method = RequestMethod.POST)
    public @ResponseBody
    ResponseResult getMchList() {
        logger.info("获取商户下拉列表开始");
        List<DropDownList> mchInfo = mchInfoMapper.getMchList();
        return ResponseResult.buildOK(mchInfo);
    }

    @ApiOperation(value = "新增商户")
    @RequestMapping(value = "insertMch", method = RequestMethod.POST)
    public @ResponseBody
    ResponseResult insertMch(@RequestBody MchInfo mchInfo) {
        logger.info("新增商户开始");
        boolean isInsert = mchInfoMapper.insertMch(mchInfo);
        logger.info("新增商户:{}", isInsert ? "成功" : "失败");
        return ResponseResult.buildOK(isInsert);
    }


}
