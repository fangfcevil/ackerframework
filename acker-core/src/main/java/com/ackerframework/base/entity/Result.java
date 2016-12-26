package com.ackerframework.base.entity;


import java.io.Serializable;
import java.util.HashMap;

public class Result implements Serializable {

    private static final long serialVersionUID = 1L;

    public Result() {

    }

    public Result(Object data) {
        this.data = data;
    }

    public Result(Object data, String message) {
        this.data = data;
        this.message = message;
    }

    public Result(Boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    //返回状态 true 成功；false 失败
    private Boolean status = true;
    //成功时返回的数据
    private Object data;
    //返回消息
    private String message = "";
    //返回错误的消息列表
    private HashMap<String, Object> error = new HashMap();

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public HashMap<String, Object> getError() {
        return error;
    }

    public void setError(HashMap<String, Object> error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
