package com.chatapp.dto.request.post;

public class PostUserGetRequestDTO {
    private Long userId;
    private Long postId;

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPostId() {
        return this.postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

}
