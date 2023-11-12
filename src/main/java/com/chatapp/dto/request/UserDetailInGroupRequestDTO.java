package com.chatapp.dto.request;

import lombok.Data;

@Data
public class UserDetailInGroupRequestDTO {
    private Long userId;
    private String groupCode;
    private Long userLogin;
}
