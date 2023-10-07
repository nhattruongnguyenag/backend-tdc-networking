package com.chatapp.dto.request;

import lombok.Data;

@Data
public class NotificationChangeStatusRequestDTO {
    private Long id;
    private Long userId;
}