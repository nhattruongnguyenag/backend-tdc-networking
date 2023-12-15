package com.chatapp.service;

import java.util.List;

import com.chatapp.converter.request.notification.NotificationChangeAllStatusByUserIdRequest;
import com.chatapp.dto.request.notification.NotificationByUserRequestDTO;
import com.chatapp.dto.request.notification.NotificationChangeStatusRequestDTO;
import com.chatapp.dto.request.notification.NotificationDeleteRequestDTO;
import com.chatapp.dto.request.notification.NotificationSaveRequestDTO;
import com.chatapp.dto.response.notification.NotificationResponseDTO;

public interface NotificationService {
    List<NotificationResponseDTO> findAll();
    List<NotificationResponseDTO> findById(NotificationByUserRequestDTO notificationByUserRequestDTO);
    List<NotificationResponseDTO> findByContent(String content);
    NotificationResponseDTO save(NotificationSaveRequestDTO notificationSaveRequestDTO);
    void addNotification(String content, String type, Long userId, String data, Long userInteracted);
    String delete(NotificationDeleteRequestDTO notificationDeleteRequestDTO);
    String changeStatusAll(NotificationChangeAllStatusByUserIdRequest notificationChangeAllStatusByUserIdRequest);
    NotificationResponseDTO changeStatus(NotificationChangeStatusRequestDTO notificationChangeStatusRequestDTO);
    NotificationResponseDTO makeNotSeen(NotificationChangeStatusRequestDTO notificationChangeStatusRequestDTO);
    Integer getCountNotification(NotificationByUserRequestDTO notificationByUserRequestDTO);
}
