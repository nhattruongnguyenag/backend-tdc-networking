package com.chatapp.dto.request.user.post_save;

import lombok.Data;

@Data
public class UserSavePostRequestDTO {
    private Long userId;
    private Long postId;
}
