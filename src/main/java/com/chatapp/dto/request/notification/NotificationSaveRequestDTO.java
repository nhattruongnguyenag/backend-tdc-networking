package com.chatapp.dto.request.notification;

import lombok.Data;

@Data
public class NotificationSaveRequestDTO {
    private Long userId;
    private String content;
    private String type;
    private String data;
}