package com.chatapp.dto.request.user;

import lombok.Data;

@Data
public class UserFindRequestDTO {
    private Long userId;
    private String search;
}
