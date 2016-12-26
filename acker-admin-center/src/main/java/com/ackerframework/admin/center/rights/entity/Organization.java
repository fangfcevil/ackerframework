package com.ackerframework.admin.center.rights.entity;

import com.ackerframework.base.entity.BaseEntity;

public class Organization extends BaseEntity{
    private Integer pid;
    private String orgName;
    private String orgCode;
    private String orgType;
    private String orgState;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getOrgState() {
        return orgState;
    }

    public void setOrgState(String orgState) {
        this.orgState = orgState;
    }
}
