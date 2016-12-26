package com.ackerframework.admin.center.rights.entity;

import com.ackerframework.base.entity.BaseEntity;

public class Navigator extends BaseEntity {

    private Integer pid;
    private Integer sno;
    private String navName;
    private String navUrl;
    private String navType;
    private String permission;
    //..Easyui 树固定字段
    private String text;
    private String iconCls;
    private String attributes;
    private String state = "closed";

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getSno() {
        return sno;
    }

    public void setSno(Integer sno) {
        this.sno = sno;
    }

    public String getNavName() {
        return navName;
    }

    public void setNavName(String navName) {
        this.navName = navName;
    }

    public String getNavUrl() {
        return navUrl;
    }

    public void setNavUrl(String navUrl) {
        this.navUrl = navUrl;
    }

    public String getNavType() {
        return navType;
    }

    public void setNavType(String navType) {
        this.navType = navType;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getText() {
        return getNavName();
    }

    public void setText(String text) {
        setNavName(text);
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public String getState() {
        return "D".equals(this.navType) ? "closed" : "open";
    }

    public void setState(String state) {
        this.state = state;
    }
}