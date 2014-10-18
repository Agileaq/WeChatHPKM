package com.hp.cdc.km.web.entity;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * WechatUser: zhongl
 * Date: 9/10/14
 * Time: 2:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class JsonResult<T> implements Serializable {
    private T data;
    private String message;
    private boolean success;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public JsonResult() {
    }

    public JsonResult(T data) {
        this.data = data;
        this.success = true;
    }

    public JsonResult(T data, String message) {
        this.data = data;
        this.message = message;
        this.success = true;
    }

    public JsonResult(T data, String message, boolean success) {
        this.data = data;
        this.message = message;
        this.success = success;
    }

}
