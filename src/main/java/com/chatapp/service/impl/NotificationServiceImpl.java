package com.chatapp.service.impl;

import java.util.List;

import com.chatapp.converter.response.NotificationResponseConverter;
import com.chatapp.dto.response.NotificationResponseDTO;
import com.chatapp.repository.NotificationRepository;
import com.chatapp.service.NotificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationResponseConverter notificationResponseConverter;
    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public List<NotificationResponseDTO> findAll() {
        return notificationResponseConverter.toDTOGroup(notificationRepository.findAll());
    }
}
