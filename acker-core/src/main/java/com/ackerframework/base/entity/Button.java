package com.ackerframework.base.entity;

import java.util.List;

public class Button {

    private String text;
    private String icon;
    private String onClick;
    private String fnParam;
    private String className = "easyui-linkbutton";
    private List<Button> buttons;

    public Button() {
    }

    public Button(String icon) {
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getOnClick() {
        return onClick;
    }

    public void setOnClick(String onClick) {
        this.onClick = onClick;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Button> getButtons() {
        return buttons;
    }

    public void setButtons(List<Button> buttons) {
        this.buttons = buttons;
    }

    public String getFnParam() {
        return fnParam;
    }

    public void setFnParam(String fnParam) {
        this.fnParam = fnParam;
    }
}
