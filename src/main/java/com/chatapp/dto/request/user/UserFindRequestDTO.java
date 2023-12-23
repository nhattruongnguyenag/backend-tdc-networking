package com.chatapp.dto.request.user;

public class UserFindRequestDTO {
    private Long userId;
    private String search;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
