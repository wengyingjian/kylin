package com.wengyingjian.kylin.common.model;

/**
 * 公用返回值结构
 * 
 */
@SuppressWarnings("serial")
public class Result<T> implements java.io.Serializable {

    private int status;
    private String msg;
    private T data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
