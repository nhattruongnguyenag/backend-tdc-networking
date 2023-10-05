package com.chatapp.dto.request;

import lombok.Data;

@Data
public class NotificationSaveRequestDTO {
    private Long userId;
    private String content;
}