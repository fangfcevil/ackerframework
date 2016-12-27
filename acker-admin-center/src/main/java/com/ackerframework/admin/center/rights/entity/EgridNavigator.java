package com.ackerframework.admin.center.rights.entity;

import com.ackerframework.base.entity.Button;
import com.ackerframework.utils.Constant;
import com.ackerframework.utils.GenerateEasyUtils;

import java.util.ArrayList;
import java.util.List;

public class EgridNavigator extends Navigator {
    private String gridOptions;

    //..treeGrid 第一级的父节点必须是Null
    private Integer _parentId;

    public Integer get_parentId() {
        return this.getPid() == 0 ? null : this.getPid();
    }

    public void set_parentId(Integer _parentId) {
        this._parentId = _parentId;
        this.setPid(_parentId);
    }

    public String getGridOptions() {
        List<Button> buttons = new ArrayList<>();
        //..查看
        Button lookButton = new Button();
        lookButton.setFnParam(this.getId().toString());
        lookButton.setOnClick("lookItem");
        lookButton.setIcon(Constant.ICON_LOOK);
        buttons.add(lookButton);
        //..修改
        Button updateButton = new Button();
        updateButton.setFnParam(this.getId().toString());
        updateButton.setOnClick("updateItem");
        updateButton.setIcon(Constant.ICON_UPATE);
        buttons.add(updateButton);
        //..删除
        Button deleteButton = new Button();
        deleteButton.setFnParam(this.getId().toString());
        deleteButton.setOnClick("deleteItem");
        deleteButton.setIcon(Constant.ICON_DELETE);
        buttons.add(deleteButton);
        return GenerateEasyUtils.buttonsHtml(buttons);
    }

    public void setGridOptions(String gridOptions) {
        this.gridOptions = gridOptions;
    }
}
