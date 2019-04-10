package com.dazhukeji.douwu.bean.publicBean;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/12/28 16:36
 * 功能描述：
 */
public class Response <T> {
    private int code;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
