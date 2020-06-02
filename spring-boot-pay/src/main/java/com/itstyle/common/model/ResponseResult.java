package com.itstyle.common.model;

import com.itstyle.common.constants.SysConstantInfo;
import com.itstyle.common.enums.ResultEnum;
import com.itstyle.common.exception.SysException;

import java.io.Serializable;

/**
 * 返回值实体类
 * <p>Title: ResponseResult</p>
 *
 * @author JDev
 * @date 2018/11/13 日
 */
public class ResponseResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;

    private String msg;

    private Object data;

    public ResponseResult(String msg) {
        this.msg = msg;
    }

    public ResponseResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * @Description:
     * @Param: []
     * @return: com.tre.jdevtemplateboot.common.pojo.ResponseResult
     * @Author: JDev
     * @Date: 2018/11/22
     **/
    public static ResponseResult buildOK() {
        return new ResponseResult(SysConstantInfo.SERVER_SUCCESS_CODE, SysConstantInfo.SERVER_SUCCESS_MSG);
    }

    /**
     * @Description:
     * @Param: [code, msg, data]
     * @return: com.tre.jdevtemplateboot.common.pojo.ResponseResult
     * @Author: JDev
     * @Date: 2018/11/22
     **/
    public static ResponseResult buildOK(Object data) {
        return new ResponseResult(SysConstantInfo.SERVER_SUCCESS_CODE, SysConstantInfo.SERVER_SUCCESS_MSG, data);
    }

    /**
     * @Description:
     * @Param: [msg, data]
     * @return: com.tre.jdevtemplateboot.common.pojo.ResponseResult
     * @Author: JDev
     * @Date: 2019/03/27
     **/
    public static ResponseResult buildOK(String msg, Object data) {
        return new ResponseResult(SysConstantInfo.SERVER_SUCCESS_CODE, msg, data);
    }


    /**
     * @Description:
     * @Param: []
     * @return: com.tre.jdevtemplateboot.common.pojo.ResponseResult
     * @Author: JDev
     * @Date: 2019/03/27
     **/
    public static ResponseResult buildError() {
        return new ResponseResult(SysConstantInfo.SERVER_REQUEST_RESULT_CODE, SysConstantInfo.SERVER_REQUEST_RESULT_MSG);
    }


    /**
     * @Description:
     * @Param: [code, msg]
     * @return: com.tre.jdevtemplateboot.common.pojo.ResponseResult
     * @Author: JDev
     * @Date: 2019/03/27
     **/
    public static ResponseResult buildError(String code, String msg) {
        return new ResponseResult(code, msg);
    }

    /**
     * @Description:
     * @Param: [resultEnum]
     * @return: com.tre.jdevtemplateboot.common.pojo.ResponseResult
     * @Author: JDev
     * @Date: 2019/03/27
     **/
    public static ResponseResult buildError(ResultEnum resultEnum) {
        return new ResponseResult(resultEnum.getCode(), resultEnum.getMsg());
    }

    /**
     * @Description:
     * @Param: [sysException]
     * @return: com.tre.jdevtemplateboot.common.pojo.ResponseResult
     * @Author: JDev
     * @Date: 2019/03/27
     **/
    public static ResponseResult buildError(SysException sysException) {
        return new ResponseResult(sysException.getCode(), sysException.getMsg());
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
