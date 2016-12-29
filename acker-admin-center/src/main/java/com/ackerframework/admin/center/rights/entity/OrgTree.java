package com.ackerframework.admin.center.rights.entity;


import java.util.List;

public class OrgTree extends Org {
    private String text;
    private String state;
    private Boolean checked;
    private List<OrgTree> orgTrees;

    public String getText() {
        return this.getOrgName();
    }

    public void setText(String text) {
        this.text = text;
        this.setOrgName(text);
    }

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

    public List<OrgTree> getOrgTrees() {
        return orgTrees;
    }

    public void setOrgTrees(List<OrgTree> orgTrees) {
        this.orgTrees = orgTrees;
    }
}
