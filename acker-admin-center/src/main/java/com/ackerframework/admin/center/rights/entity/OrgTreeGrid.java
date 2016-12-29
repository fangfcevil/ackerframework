package com.ackerframework.admin.center.rights.entity;

public class OrgTreeGrid extends Org {

    private String gridOptions;
    private Integer _parentId;

    //..treeGrid 第一级的父节点必须是Null
    public Integer get_parentId() {
        return this.getPid() == 0 ? null : this.getPid();
    }

    public void set_parentId(Integer _parentId) {
        this._parentId = _parentId;
        this.setPid(_parentId);
    }

    public String getGridOptions() {
        return gridOptions;
    }

    public void setGridOptions(String gridOptions) {
        this.gridOptions = gridOptions;
    }
}
