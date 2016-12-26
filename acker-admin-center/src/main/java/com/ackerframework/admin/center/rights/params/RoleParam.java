package com.ackerframework.admin.center.rights.params;


import com.ackerframework.base.entity.PageParam;

import javax.servlet.http.HttpServletRequest;

public class RoleParam extends PageParam {

    public RoleParam(HttpServletRequest request) {
        super(request);
        this.roleName = request.getParameter("roleName");
    }

    public RoleParam(String roleName) {
        this.roleName = roleName;
    }

    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
