package com.chatapp.service.impl;

import com.chatapp.dto.request.notification.PushNotificationRequestDTO;
import com.chatapp.entity.DeviceTokenEntity;
import com.chatapp.entity.UserEntity;
import com.chatapp.repository.UserRepository;
import com.chatapp.service.FirebaseMessagingService;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FirebaseMessagingServiceImpl implements FirebaseMessagingService {
    @Autowired
    private FirebaseMessaging firebaseMessaging;
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean sendNotificationToUser(Long userId, String messageContent) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);

        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();
            if (userEntity.getMessageConnect() == true) {
                for (DeviceTokenEntity deviceToken: userEntity.getDeviceTokens()) {
                    sendNotificationToToken(getPushNotificationRequestDTO(messageContent, deviceToken, userEntity));
                }
            }
            return true;
        }

        return false;
    }

    private static PushNotificationRequestDTO getPushNotificationRequestDTO(String messageContent, DeviceTokenEntity deviceToken, UserEntity userEntity) {
        PushNotificationRequestDTO pushNotificationRequestDTO = new PushNotificationRequestDTO();
        pushNotificationRequestDTO.setTitle(userEntity.getName());
        pushNotificationRequestDTO.setMessage(messageContent);
        pushNotificationRequestDTO.setImage(userEntity.getImage());
        pushNotificationRequestDTO.setTopic("messages");
        pushNotificationRequestDTO.setToken(deviceToken.getToken());
        return pushNotificationRequestDTO;
    }

    @Override
    public boolean sendNotificationToToken(PushNotificationRequestDTO requestDTO) {
        Notification notification = Notification.builder()
                .setTitle(requestDTO.getTitle())
                .setBody(requestDTO.getMessage())
                .setImage(requestDTO.getImage())
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
