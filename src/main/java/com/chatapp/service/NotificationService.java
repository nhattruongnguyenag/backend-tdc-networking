package com.chatapp.service;

import java.util.List;

import com.chatapp.dto.request.NotificationChangeStatusRequestDTO;
import com.chatapp.dto.request.NotificationDeleteRequestDTO;
import com.chatapp.dto.request.NotificationSaveRequestDTO;
import com.chatapp.dto.response.NotificationResponseDTO;

public interface NotificationService {
    List<NotificationResponseDTO> findAll();
    List<NotificationResponseDTO> findById(Long id);
    NotificationResponseDTO save(NotificationSaveRequestDTO notificationSaveRequestDTO);
    String delete(NotificationDeleteRequestDTO notificationDeleteRequestDTO);
    String deleteAll(Long userId);
    NotificationResponseDTO changeStatus(NotificationChangeStatusRequestDTO notificationChangeStatusRequestDTO);
}
