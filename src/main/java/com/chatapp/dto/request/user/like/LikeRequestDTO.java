package com.chatapp.dto.request.user.like;

import com.chatapp.dto.BaseDTO;

public class LikeRequestDTO extends BaseDTO{
    private Long postId;
    private Long userId;
    private String type;
    private String search;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSearch() {
        return this.search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

}
