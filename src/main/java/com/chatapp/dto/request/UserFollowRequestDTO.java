package com.chatapp.dto.request;

import lombok.Data;

@Data
public class UserFollowRequestDTO {
    private Long userId;
    private Long userFollowId;
}
