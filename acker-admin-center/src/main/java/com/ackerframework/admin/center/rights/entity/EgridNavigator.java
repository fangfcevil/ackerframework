package com.ackerframework.admin.center.rights.entity;

public class EgridNavigator extends Navigator {
    private String gridOptions;
    private Integer _parentId;

    public Integer get_parentId() {
        return this.getPid();
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
