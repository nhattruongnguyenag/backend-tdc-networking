package com.chatapp.dto.request;

import lombok.Data;

@Data
public class AllPostByUserAndGroupResponseDTO {
    Long userLogin;
    Long userId;
    String code;
}
