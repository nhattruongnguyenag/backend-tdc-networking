package com.chatapp.dto.request.user.image;

import lombok.Data;

@Data
public class UserImageUpdateRequestDTO {
    private Long userId;
    private String avatar;
    private String backgroud;
}
