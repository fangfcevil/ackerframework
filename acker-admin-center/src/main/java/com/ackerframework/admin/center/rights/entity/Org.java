package com.ackerframework.admin.center.rights.entity;

import com.ackerframework.base.entity.BaseEntity;

public class Org extends BaseEntity {
    private Integer pid;
    private String text;
    private String coded;
    private String type;
    private Boolean canUse;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCoded() {
        return coded;
    }

    public void setCoded(String coded) {
        this.coded = coded;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getCanUse() {
        return canUse;
    }

    public void setCanUse(Boolean canUse) {
        this.canUse = canUse;
    }
}
