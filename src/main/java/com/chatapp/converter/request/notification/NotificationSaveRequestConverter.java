package com.chatapp.converter.request.notification;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.request.notification.NotificationSaveRequestDTO;
import com.chatapp.entity.NotificationEntity;
import com.chatapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationSaveRequestConverter extends BaseConverter<NotificationEntity, NotificationSaveRequestDTO>{

    @Autowired
    private UserRepository userRepository;
    @Override
    public NotificationEntity toEntity(NotificationSaveRequestDTO dto) {
        NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setContent(dto.getContent());
        notificationEntity.setUser(userRepository.findOneById(dto.getUserId()));
        notificationEntity.setContent(dto.getContent());
        notificationEntity.setType(dto.getType());
        notificationEntity.setData(dto.getData());
        notificationEntity.setStatus((byte)0);
        return notificationEntity;
    }
}