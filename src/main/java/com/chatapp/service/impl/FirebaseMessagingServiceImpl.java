package com.chatapp.service.impl;

import com.chatapp.dto.request.PushNotificationRequestDTO;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirebaseMessagingServiceImpl {
    @Autowired
    private FirebaseMessaging firebaseMessaging;

    public boolean sendNotificationToToken(PushNotificationRequestDTO requestDTO) {
        Notification notification = Notification.builder()
                .setTitle(requestDTO.getTitle())
                .setBody(requestDTO.getMessage())
                .setImage("https://avatars.githubusercontent.com/u/842078?s=96&v=4")
                .build();

        Message message = Message.builder()
                .setToken(requestDTO.getToken())
                .setNotification(notification)
                .build();

        try {
            firebaseMessaging.send(message);
            return true;
        } catch (FirebaseMessagingException e) {
            return false;
        }
    }
}
