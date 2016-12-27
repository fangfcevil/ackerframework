package com.ackerframework.admin.center.rights.entity;

public class EtreeNavigator extends Navigator {

    private String text;
    private String state = "closed";
    private Boolean checked = false;

    public String getText() {
        return this.getNavName();
    }

    public void setText(String text) {
        this.text = text;
        this.setNavName(text);
    }

    public String getState() {
        return "D".equals(this.getNavType()) ? "closed" : "open";
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
