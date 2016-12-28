package com.ackerframework.admin.center.base.entity;

import com.ackerframework.base.entity.BaseEntity;

public class BaseData extends BaseEntity {

    private Integer classId;
    private String valueField;
    private String textField;
    private Boolean sysMark;
    private Boolean useMark;
    private String remark;

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getValueField() {
        return valueField;
    }

    public void setValueField(String valueField) {
        this.valueField = valueField;
    }

    public String getTextField() {
        return textField;
    }

    public void setTextField(String textField) {
        this.textField = textField;
    }

    public Boolean getSysMark() {
        return sysMark;
    }

    public void setSysMark(Boolean sysMark) {
        this.sysMark = sysMark;
    }

    public Boolean getUseMark() {
        return useMark;
    }

    public void setUseMark(Boolean useMark) {
        this.useMark = useMark;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
