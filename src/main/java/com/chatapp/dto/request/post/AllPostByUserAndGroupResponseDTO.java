package com.chatapp.dto.request.post;

import lombok.Data;

@Data
public class AllPostByUserAndGroupResponseDTO {
    Long userLogin;
    Integer userId;
    String code;
}
