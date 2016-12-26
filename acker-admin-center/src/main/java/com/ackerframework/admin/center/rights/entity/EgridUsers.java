package com.ackerframework.admin.center.rights.entity;

import com.ackerframework.base.entity.Button;
import com.ackerframework.utils.Constant;
import com.ackerframework.utils.GenerateEasyUtils;

import java.util.ArrayList;
import java.util.List;

public class EgridUsers extends User {

    private String gridOptions;
    private String photoToGrid;

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
        if (!"ackerman".equals(this.getUserName())) {
            Button deleteButton = new Button();
            deleteButton.setFnParam(this.getId().toString());
            deleteButton.setOnClick("deleteItem");
            deleteButton.setIcon(Constant.ICON_DELETE);
            buttons.add(deleteButton);
        }
        return GenerateEasyUtils.buttonsHtml(buttons);
    }

    public void setGridOptions(String gridOptions) {
        this.gridOptions = gridOptions;
    }

    public String getPhotoToGrid() {
        return photoToGrid;
    }

    public void setPhotoToGrid(String photoToGrid) {
        this.photoToGrid = photoToGrid;
    }
}
