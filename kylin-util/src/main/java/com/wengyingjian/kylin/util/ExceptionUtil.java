package com.wengyingjian.kylin.util;

import com.wengyingjian.kylin.common.exception.ServiceException;

/**
 * Created by wengyingjian on 16/1/29.
 */
public class ExceptionUtil {
    public static void gen(String msg) {
        throw new ServiceException(-1, msg);
    }

    public static void gen(String msg, Throwable e) {
        throw new ServiceException(-1, msg, e);
    }

    public static void gen(Throwable e) {
        throw new ServiceException(e);
    }
}
