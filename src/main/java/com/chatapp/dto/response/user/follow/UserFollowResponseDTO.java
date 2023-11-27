package com.chatapp.dto.response.user.follow;

import com.chatapp.entity.UserEntity;

import lombok.Data;

@Data
public class UserFollowResponseDTO {
    private Long id;
    private String name;
    private String image;
    private Boolean isFollow;
}
