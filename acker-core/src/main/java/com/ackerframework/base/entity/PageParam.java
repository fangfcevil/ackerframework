package com.ackerframework.base.entity;


import com.ackerframework.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class PageParam extends BaseParam {
    private Integer pageSize = 10;
    private Integer pageNo = 1;
    private Integer startIndex = 0;
    private String sortFeild;
    private String order;

    public PageParam() {
    }


    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }


    public String getSortFeild() {
        return sortFeild;
    }

    public void setSortFeild(String sortFeild) {
        this.sortFeild = sortFeild;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public PageParam(HttpServletRequest request) {
        if (StringUtils.isNotBlank(request.getParameter("rows"))) {
            setPageSize(Integer.parseInt(request.getParameter("rows")));
        }
        if (StringUtils.isNotBlank(request.getParameter("page"))) {
            setPageNo(Integer.parseInt(request.getParameter("page")));
        }
        if (StringUtils.isNotBlank(request.getParameter("sort"))) {
            setSortFeild(request.getParameter("sort"));
        }
        if (StringUtils.isNotBlank(request.getParameter("order"))) {
            setOrder(request.getParameter("order"));
        }
        setStartIndex((getPageNo() - 1) * getPageSize());
    }
}
