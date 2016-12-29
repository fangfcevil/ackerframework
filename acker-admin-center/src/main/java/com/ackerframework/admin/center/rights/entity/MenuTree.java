package com.ackerframework.admin.center.rights.entity;

public class MenuTree extends Menu {

    private String state;
    private Boolean checked;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
