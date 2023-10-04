package com.chatapp.service;

import java.util.List;

import com.chatapp.dto.response.NotificationResponseDTO;

public interface NotificationService {
    List<NotificationResponseDTO> findAll();
}
