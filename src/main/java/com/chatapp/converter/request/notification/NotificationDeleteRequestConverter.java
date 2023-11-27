package com.chatapp.converter.request.notification;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.request.notification.NotificationDeleteRequestDTO;
import com.chatapp.entity.NotificationEntity;
import org.springframework.stereotype.Component;

@Component
public class NotificationDeleteRequestConverter extends BaseConverter<NotificationEntity, NotificationDeleteRequestDTO>{
}
