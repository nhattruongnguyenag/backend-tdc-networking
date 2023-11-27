package com.chatapp.dto.request.user.like;

import com.chatapp.dto.BaseDTO;

import lombok.Data;

@Data
public class LikeRequestDTO extends BaseDTO{
    private Long postId;
    private Long userId;
}
