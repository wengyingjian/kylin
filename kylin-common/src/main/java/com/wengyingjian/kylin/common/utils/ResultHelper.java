/**
 * Copyright (c) 2015, 59store. All rights reserved.
 */
package com.wengyingjian.kylin.common.utils;

import com.wengyingjian.kylin.common.model.Result;

/**
 *
 * @author <a href="mailto:chenyb@59store.com">山人</a>
 * @version 1.0 15/11/12
 * @since 1.0
 */
public class ResultHelper {

    public static Result genResult(Object obj, int retCode, String msg) {
        Result ret = new Result();
        ret.setData(obj);
        ret.setMsg(msg);
        ret.setStatus(retCode);
        return ret;
    }

    public static Result genResult(int retCode, String msg) {
        Result ret = new Result();
        ret.setMsg(msg);
        ret.setStatus(retCode);
        return ret;
    }

    public static Result genResultWithSuccess(Object obj) {
        return genResult(obj, 0, "");
    }

    public static Result genResultWithSuccess() {
        return genResult(0, "");
    }

}
