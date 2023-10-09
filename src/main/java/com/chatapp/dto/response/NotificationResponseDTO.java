package com.chatapp.dto.response;

import com.chatapp.dto.BaseDTO;

import lombok.Data;

@Data
public class NotificationResponseDTO extends BaseDTO{
    private String content;
    private String status;
    private UserInfoResponseDTO user;
}
