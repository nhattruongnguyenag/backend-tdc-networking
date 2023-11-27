package com.chatapp.dto.request.notification;

import lombok.Data;

@Data
public class NotificationChangeStatusRequestDTO {
    private Long id;
    private Long userId;
}