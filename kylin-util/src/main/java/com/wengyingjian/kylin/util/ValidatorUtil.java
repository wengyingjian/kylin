/**
 * Copyright (c) 2015, 59store. All rights reserved.
 */
package com.wengyingjian.kylin.util;

/**
 *
 * @author <a href="mailto:chenyb@59store.com">山人</a>
 * @version 1.0 15/11/6
 * @since 1.0
 */
public class ValidatorUtil {

    /**
     * 简单验证手机号码的格式及是否存在
     *
     * @param phone
     *            待验证的手机号码
     * @return 手机号码格式正确且存在一定返回true. 手机号码格式错误返回false, 手机号码不存在极大概率返回false
     */
    public static boolean isPhoneNumber(String phone) {
        String phoneRegex = "1[34578]\\d{9}";
        return phone.matches(phoneRegex);
    }

}
