package com.ackerframework.admin.center.rights.entity;

import com.ackerframework.base.entity.Button;
import com.ackerframework.utils.Constant;
import com.ackerframework.utils.GenerateEasyUtils;

import java.util.ArrayList;
import java.util.List;

public class RightsGrid extends Rights {

    private String gridOptions;
    private String roleCode;
    private String nickname;
    private String account;
    private String roleName;
    private String roleIds;

    public String getGridOptions() {
        List<Button> buttons = new ArrayList<>();
        //..删除
        Button deleteButton = new Button();
        deleteButton.setFnParam(this.getOrgId().toString() + "," + this.getUserId().toString());
        deleteButton.setOnClick("deleteItem");
        deleteButton.setIcon(Constant.ICON_DELETE);
        buttons.add(deleteButton);
        return GenerateEasyUtils.buttonsHtml(buttons);
    }

    public void setGridOptions(String gridOptions) {
        this.gridOptions = gridOptions;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }
}
