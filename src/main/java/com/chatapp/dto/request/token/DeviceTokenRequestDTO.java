package com.chatapp.dto.request.token;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeviceTokenRequestDTO {
    private Long userId;
    private String deviceToken;
}
