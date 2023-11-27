package com.chatapp.dto.request.user;

import lombok.Data;

@Data
public class UserInfoFindRequestDTO {
    private Long userId;
    private String type;
    private String name;
    private Long userFollowId;
}
