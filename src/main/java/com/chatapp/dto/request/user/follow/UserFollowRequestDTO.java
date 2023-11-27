package com.chatapp.dto.request.user.follow;

import lombok.Data;

@Data
public class UserFollowRequestDTO {
    private Long userId;
    private Long userFollowId;
}
