package com.ackerframework.admin.center.rights.params;


import com.ackerframework.base.entity.PageParam;

import javax.servlet.http.HttpServletRequest;

public class UserParam extends PageParam {

    public UserParam(HttpServletRequest request) {
        super(request);
        this.userName = request.getParameter("userName");
    }

    public UserParam(String userName) {
        this.userName = userName;
    }

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
