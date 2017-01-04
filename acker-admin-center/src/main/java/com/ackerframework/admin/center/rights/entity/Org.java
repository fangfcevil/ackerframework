package com.ackerframework.admin.center.rights.entity;

import com.ackerframework.base.entity.BaseTreeEntity;

public class Org extends BaseTreeEntity {
    private Integer pid;
    private String coded;
    private String types;
    private Boolean canUse;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getCoded() {
        return coded;
    }

    public void setCoded(String coded) {
        this.coded = coded;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public Boolean getCanUse() {
        return canUse;
    }

    public void setCanUse(Boolean canUse) {
        this.canUse = canUse;
    }
}
