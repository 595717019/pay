package com.itstyle.common.exception;

import com.itstyle.common.constants.SysConstantInfo;
import com.itstyle.common.model.ResponseResult;
import com.itstyle.common.utils.LJsonUtils;
import com.itstyle.common.utils.LLoggerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 全局异常处理
 * @author: JDev
 * @create: 2019-03-26 16:30
 **/

@Component
public class SysExceptionHandler implements HandlerExceptionResolver {

    private static Logger LOGGER = LoggerFactory.getLogger(SysExceptionHandler.class);
    private static Logger logBusiness = LLoggerUtils.Logger(LLoggerUtils.LogFileName.Business);

    private static String SysExceptionBaseInfo = "全局异常处理：";


    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response
            , Object handler, Exception ex) {

        SysException sysException = null;
        if (ex instanceof DuplicateKeyException) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            sysException = new SysException(SysConstantInfo.SERVER_DUPLICATEKEY_CODE, SysConstantInfo.SERVER_DUPLICATEKEY_MSG);
            LOGGER.error(SysExceptionBaseInfo + ex.getMessage(), ex);
        } else if (ex instanceof SysException) {
            response.setStatus(HttpStatus.OK.value());
            sysException = (SysException) ex;
            logBusiness.info("--------------Response 全局异常处理 Start----------------");
            logBusiness.info(SysExceptionBaseInfo + sysException.getMsg());
            logBusiness.info("--------------Response 全局异常处理 End----------------");
            LOGGER.error(SysExceptionBaseInfo + sysException.getMsg(), sysException);
        } else if (ex instanceof SysServletJwtException) {
            response.setStatus(HttpStatus.OK.value());
            SysServletJwtException extt = (SysServletJwtException) ex;
            sysException = new SysException(extt.getCode(), extt.getMsg());
            logBusiness.info("--------------Response JWT Token 认证异常 Start--------------");
            logBusiness.info(SysExceptionBaseInfo + extt.getMsg());
            logBusiness.info("--------------Response JWT Token 认证异常 End----------------");
            LOGGER.error(SysExceptionBaseInfo + extt.getMsg(), extt);
        } else {
            // 如果抛出的不是系统自定义异常则重新构造一个系统错误异常。
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            sysException = new SysException(SysConstantInfo.SERVER_ERROR_CODE, SysConstantInfo.SERVER_ERROR_MSG);
            LOGGER.error(SysExceptionBaseInfo + ex.getMessage(), ex);
        }

        // ContentType
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
        /* response */
        String jsonstr = LJsonUtils.objectToJson(ResponseResult.buildError(sysException));
        try {
            response.getWriter().write(jsonstr);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ModelAndView();
    }
}
