package com.ackerframework.base.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据Entity类
 */
public abstract class SimpleEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    public SimpleEntity() {

    }

    public SimpleEntity(Integer id) {
        this();
        this.id = id;
    }

    protected Integer id; //主键


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
}
