package com.chatapp.service;

import com.chatapp.dto.request.DeviceTokenRequestDTO;

public interface DeviceTokenService {
    boolean saveUserDeviceToken(DeviceTokenRequestDTO requestDTO);
}
