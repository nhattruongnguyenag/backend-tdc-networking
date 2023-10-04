package com.chatapp.dto.response;

import lombok.Data;

@Data
public class NotificationResponseDTO {
    private String content;
    private String status;
    private UserInfoResponseDTO user;
}
