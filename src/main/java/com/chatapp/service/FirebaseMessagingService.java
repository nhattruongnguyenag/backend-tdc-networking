package com.chatapp.service;

import com.chatapp.dto.request.notification.PushNotificationRequestDTO;

public interface FirebaseMessagingService {
    boolean sendNotificationToUser(Long userId, String messageContent);

    boolean sendNotificationToToken(PushNotificationRequestDTO requestDTO);
}
