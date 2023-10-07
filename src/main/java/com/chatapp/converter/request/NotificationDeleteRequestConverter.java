package com.chatapp.converter.request;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.request.NotificationDeleteRequestDTO;
import com.chatapp.entity.NotificationEntity;
import org.springframework.stereotype.Component;

@Component
public class NotificationDeleteRequestConverter extends BaseConverter<NotificationEntity, NotificationDeleteRequestDTO>{
}
