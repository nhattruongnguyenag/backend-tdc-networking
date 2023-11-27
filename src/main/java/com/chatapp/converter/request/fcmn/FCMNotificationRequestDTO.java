package com.chatapp.converter.request.fcmn;

import lombok.Data;

@Data
public class FCMNotificationRequestDTO {
    private Long userId;
    private String content;
}
