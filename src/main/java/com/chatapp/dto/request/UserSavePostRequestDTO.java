package com.chatapp.dto.request;

import lombok.Data;

@Data
public class UserSavePostRequestDTO {
    private Long userId;
    private Long postId;
}
