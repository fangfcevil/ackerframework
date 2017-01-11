package com.ackerframework.base.entity;

import java.io.Serializable;

public class APIResult implements Serializable {
    private static final long serialVersionUID = 1L;


    private Integer errCode = 0;
    private Object errMsg = "ok";

    public APIResult() {
    }

    public APIResult(Object errMsg) {
        this.errMsg = errMsg;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public Object getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(Object errMsg) {
        this.errMsg = errMsg;
    }
}
