package com.ackerframework.base.entity;

public abstract class KeyEntity extends BaseEntity {

    protected Integer id; //主键

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
