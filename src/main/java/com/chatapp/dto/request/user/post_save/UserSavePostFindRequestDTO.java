package com.chatapp.dto.request.user.post_save;

public class UserSavePostFindRequestDTO {
    private Long userId;
    private String search;

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSearch() {
        return this.search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
