package com.chatapp.dto;

public class Pagination {
    private Integer limit;
    private Integer offset;

    public Pagination() {
    }

    public Pagination(Integer limit) {
        this.limit = limit;
    }

    public Pagination(Integer limit, Integer offset) {
        this.limit = limit;
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
