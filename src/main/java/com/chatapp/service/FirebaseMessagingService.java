package com.chatapp.service;

import com.chatapp.dto.request.PushNotificationRequestDTO;

public interface FirebaseMessagingService {
    boolean sendNotificationToToken(PushNotificationRequestDTO requestDTO);
}
