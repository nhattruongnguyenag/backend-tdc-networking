package com.chatapp.converter.request;

import lombok.Data;

@Data
public class FCMNotificationRequestDTO {
    private Long userId;
    private String content;
}
