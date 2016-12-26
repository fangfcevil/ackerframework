package com.ackerframework.base.entity;

import java.util.ArrayList;
import java.util.List;

public class EasyPage<T> {
    private int total = 1; // 总记录数
    private int pageNo = 1; // 当前页码
    private int pageSize = 10;// 每页数

    private List<T> rows = new ArrayList<T>();
    private List footer = new ArrayList();

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public List getFooter() {
        return footer;
    }

    public void setFooter(List footer) {
        this.footer = footer;
    }

    /**
     * 本次查询第一条
     */
    public int getFirstResult() {
        int firstResult = (getPageNo() - 1) * getPageSize();
        if (firstResult >= getPageSize()) {
            firstResult = 0;
        }
        return firstResult;
    }

    /**
     * 本次查询最后一条
     */
    public int getMaxResults() {
        return getPageSize();
    }
}
