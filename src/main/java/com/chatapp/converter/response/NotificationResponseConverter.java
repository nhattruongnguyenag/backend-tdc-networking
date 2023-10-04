package com.chatapp.converter.response;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.NotificationResponseDTO;
import com.chatapp.entity.NotificationEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationResponseConverter extends BaseConverter<NotificationEntity,NotificationResponseDTO>{

    @Autowired
    private UserInfoResponseConverter userInfoResponseConverter;

    @Override
    public NotificationResponseDTO toDTO(NotificationEntity entity) {
        NotificationResponseDTO notificationResponseDTO = super.toDTO(entity);
        notificationResponseDTO.setUser(userInfoResponseConverter.toDTO(entity.getUser()));
        return notificationResponseDTO;
    }
}
  
