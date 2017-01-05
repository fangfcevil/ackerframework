package com.ackerframework.admin.center.rights.params;


import com.ackerframework.base.entity.PageParam;

import javax.servlet.http.HttpServletRequest;

public class RightsParam extends PageParam {

    public RightsParam(HttpServletRequest request) {
        super(request);
        setOrgId(Integer.parseInt(request.getParameter("orgId")));
    }

    private Integer orgId;

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }
}
