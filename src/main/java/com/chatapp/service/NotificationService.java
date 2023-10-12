package com.chatapp.service;

import java.util.List;

import com.chatapp.converter.request.NotificationChangeAllStatusByUserIdRequest;
import com.chatapp.dto.request.NotificationChangeStatusRequestDTO;
import com.chatapp.dto.request.NotificationDeleteRequestDTO;
import com.chatapp.dto.request.NotificationSaveRequestDTO;
import com.chatapp.dto.response.NotificationResponseDTO;

public interface NotificationService {
    List<NotificationResponseDTO> findAll();
    List<NotificationResponseDTO> findById(Long id);
    List<NotificationResponseDTO> findByContent(String content);
    NotificationResponseDTO save(NotificationSaveRequestDTO notificationSaveRequestDTO);
    String delete(NotificationDeleteRequestDTO notificationDeleteRequestDTO);
    String changeStatusAll(NotificationChangeAllStatusByUserIdRequest notificationChangeAllStatusByUserIdRequest);
    NotificationResponseDTO changeStatus(NotificationChangeStatusRequestDTO notificationChangeStatusRequestDTO);
    NotificationResponseDTO makeNotSeen(NotificationChangeStatusRequestDTO notificationChangeStatusRequestDTO);
}
