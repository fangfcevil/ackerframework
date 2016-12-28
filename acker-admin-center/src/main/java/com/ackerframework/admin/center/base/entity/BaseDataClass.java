package com.ackerframework.admin.center.base.entity;

import com.ackerframework.base.entity.BaseEntity;

public class BaseDataClass extends BaseEntity {
    private String classCode;
    private String className;
    private Boolean sysMark;
    private String remark;

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Boolean getSysMark() {
        return sysMark;
    }

    public void setSysMark(Boolean sysMark) {
        this.sysMark = sysMark;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
