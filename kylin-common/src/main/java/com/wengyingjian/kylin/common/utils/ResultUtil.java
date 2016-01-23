package com.wengyingjian.kylin.common.utils;

import com.wengyingjian.kylin.common.enums.ResultStatus;
import com.wengyingjian.kylin.common.model.Result;

/**
 * 返回结果工具类
 */
public class ResultUtil {

    /**
     * 生成指定的结果
     *
     * @param obj     结果中包含的数据
     * @param retCode 返回码
     * @param msg     说明信息
     * @return
     */
    public static <T> Result<T> genResult(T obj, int retCode, String msg) {
        Result<T> ret = new Result<>();
        ret.setData(obj);
        ret.setMsg(msg);
        ret.setStatus(retCode);
        return ret;
    }

    /**
     * 生成不带数据的结果
     *
     * @param retCode
     * @param msg
     * @return
     * @see ResultUtil#genResult(Object, int, String)
     */
    public static Result genResult(int retCode, String msg) {
        Result ret = new Result();
        ret.setMsg(msg);
        ret.setStatus(retCode);
        return ret;
    }

    /**
     * 生成正常返回的结果(返回码为0)
     *
     * @param obj
     * @param <T>
     * @return
     * @see ResultUtil#genResult(Object, int, String)
     * @see ResultStatus#SUCCESS
     */
    public static <T> Result<T> genSuccessResult(T obj) {
        return genResult(obj, ResultStatus.SUCCESS.getCode(), "");
    }

    /**
     * 生成不带数据且正常返回的结果(返回码为0)
     *
     * @return
     * @see ResultUtil#genResult(Object, int, String)
     * @see ResultStatus#SUCCESS
     */
    public static Result genSuccessResult() {
        return genResult(ResultStatus.SUCCESS.getCode(), "");
    }

    /**
     * 生成一般异常的结果(返回码为1)
     *
     * @param msg
     * @return * @see ResultUtil#genResult(Object, int, String)
     * @see ResultStatus#COMMON_ERROR
     */
    public static Result genCommonError(String msg) {
        return genResult(ResultStatus.COMMON_ERROR.getCode(), msg);
    }

}
