package com.chatapp.service;

import com.chatapp.dto.request.token.DeviceTokenRequestDTO;

public interface DeviceTokenService {
    boolean saveUserDeviceToken(DeviceTokenRequestDTO requestDTO);
    boolean deleteUserDeviceToken(DeviceTokenRequestDTO requestDTO);
}
