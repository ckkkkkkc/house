package com.team.house.util;

/**
 * Created by Administrator on 2019-8-11.
 */
public class PageBean {
    public PageBean() {
    }
    public PageBean(int page, int rows) {
        this.page = page;
        this.rows = rows;
    }
    private int page;//页码
    private int rows;//页大小

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "page=" + page +
                ", rows=" + rows +
                '}';
    }
}
