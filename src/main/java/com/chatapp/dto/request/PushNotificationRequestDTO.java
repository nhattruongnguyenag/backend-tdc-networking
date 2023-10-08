package com.chatapp.dto.request;

import lombok.Data;

@Data
public class PushNotificationRequestDTO {
    private String title;
    private String message;
    private String topic;
    private String token;
    private String image;
}
