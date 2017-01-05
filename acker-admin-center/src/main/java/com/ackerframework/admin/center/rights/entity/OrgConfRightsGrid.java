package com.ackerframework.admin.center.rights.entity;


import com.ackerframework.base.entity.Button;
import com.ackerframework.utils.Constant;
import com.ackerframework.utils.GenerateEasyUtils;

import java.util.ArrayList;
import java.util.List;

public class OrgConfRightsGrid extends Org {
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
        List<Button> buttons = new ArrayList<>();
        //..新增用户
        Button lookButton = new Button();
        lookButton.setFnParam(this.getId().toString());
        lookButton.setOnClick("addUser");
        lookButton.setIcon("<i class='fa fa-plus fa-fw fa-lg'></i>");
        lookButton.setText("添加用户");
        buttons.add(lookButton);
        return GenerateEasyUtils.buttonsHtml(buttons);
    }

    public void setGridOptions(String gridOptions) {
        this.gridOptions = gridOptions;
    }
}
