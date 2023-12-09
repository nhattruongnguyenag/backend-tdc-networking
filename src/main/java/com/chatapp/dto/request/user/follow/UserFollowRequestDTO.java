package com.chatapp.dto.request.user.follow;

public class UserFollowRequestDTO {
    private Long userId;
    private Long userFollowId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserFollowId() {
        return userFollowId;
    }

    public void setUserFollowId(Long userFollowId) {
        this.userFollowId = userFollowId;
    }
}
