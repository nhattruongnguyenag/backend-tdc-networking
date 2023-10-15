package com.chatapp.dto.response;

import com.chatapp.entity.UserEntity;

import lombok.Data;

@Data
public class UserFollowResponseDTO {
    private Long id;
    private String name;
    private String image;
}
