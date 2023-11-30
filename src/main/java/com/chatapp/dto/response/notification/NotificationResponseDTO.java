package com.chatapp.dto.response.notification;

import com.chatapp.dto.BaseDTO;
import com.chatapp.dto.response.user.UserInfoResponseDTO;

import lombok.Data;

@Data
public class NotificationResponseDTO extends BaseDTO{
    private String content;
    private String status;
    private String type;
    private UserInfoResponseDTO user;
}
