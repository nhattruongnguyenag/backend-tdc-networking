package com.chatapp.converter.request;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.request.NotificationChangeStatusRequestDTO;
import com.chatapp.entity.NotificationEntity;
import com.chatapp.repository.NotificationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationChangeStatusRequestConverter extends BaseConverter<NotificationEntity, NotificationChangeStatusRequestDTO>{
    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public NotificationEntity toEntity(NotificationChangeStatusRequestDTO dto) {
        NotificationEntity entity = notificationRepository.findByIdAndUser_Id(dto.getId(), dto.getUserId());
        entity.setStatus((byte)1);
        return entity;
    }
}

