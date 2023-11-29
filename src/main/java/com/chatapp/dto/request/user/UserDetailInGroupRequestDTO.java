package com.chatapp.dto.request.user;

import lombok.Data;

@Data
public class UserDetailInGroupRequestDTO {
    private Integer userId;
    private String groupCode;
    private Long userLogin;
}
