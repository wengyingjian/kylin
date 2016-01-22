/**
 * Copyright (c) 2015, 59store. All rights reserved.
 */
package com.wengyingjian.kylin.common.exception;

@SuppressWarnings("serial")
public class ServiceException extends BaseException {

    public ServiceException() {
        super();
    }

    public ServiceException(int status) {
        super(status);
    }

    public ServiceException(int status, String message) {
        super(status, message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(int status, String message, Throwable cause) {
        super(status, message, cause);
    }

}
