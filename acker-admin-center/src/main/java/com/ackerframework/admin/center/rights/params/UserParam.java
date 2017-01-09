package com.ackerframework.admin.center.rights.params;


import com.ackerframework.base.entity.PageParam;
import com.ackerframework.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class UserParam extends PageParam {

    public UserParam(HttpServletRequest request) {
        super(request);
        this.account = request.getParameter("account");
        this.nickname = request.getParameter("nickname");
        this.q = request.getParameter("q");
    }

    public UserParam(String account) {
        this.account = account;
    }

    private String account;
    private String nickname;
    private String q;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }
}
