package com.chatapp.service.impl;

import java.util.List;

import com.chatapp.converter.request.NotificationChangeStatusRequestConverter;
import com.chatapp.converter.request.NotificationDeleteRequestConverter;
import com.chatapp.converter.request.NotificationSaveRequestConverter;
import com.chatapp.converter.response.NotificationResponseConverter;
import com.chatapp.dto.request.NotificationChangeStatusRequestDTO;
import com.chatapp.dto.request.NotificationDeleteRequestDTO;
import com.chatapp.dto.request.NotificationSaveRequestDTO;
import com.chatapp.dto.response.NotificationResponseDTO;
import com.chatapp.entity.NotificationEntity;
import com.chatapp.exception.DuplicateUsernameException;
import com.chatapp.repository.NotificationRepository;
import com.chatapp.repository.UserRepository;
import com.chatapp.service.NotificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationResponseConverter notificationResponseConverter;
    @Autowired
    private NotificationSaveRequestConverter notificationSaveRequestConverter;
    @Autowired
    private NotificationDeleteRequestConverter notificationDeleteRequestConverter;
    @Autowired
    private NotificationChangeStatusRequestConverter notificationChangeStatusRequestConverter;
    @Autowired
    private NotificationRepository notificationRepository;
     @Autowired
    private UserRepository userRepository;

    @Override
    public List<NotificationResponseDTO> findAll() {
        return notificationResponseConverter.toDTOGroup(notificationRepository.findAll());
    }

    @Override
    public List<NotificationResponseDTO> findById(Long id) {
        if (userRepository.findOneById(id) == null) {
            throw new DuplicateUsernameException("user_not_exists");
        }
        return notificationResponseConverter.toDTOGroup(notificationRepository.findByUser_Id(id));
    }

    @Override
    public NotificationResponseDTO save(NotificationSaveRequestDTO notificationSaveRequestDTO) {
        if (userRepository.findOneById(notificationSaveRequestDTO.getUserId()) == null) {
            throw new DuplicateUsernameException("user_not_exists");
        }
        NotificationEntity entity = notificationSaveRequestConverter.toEntity(notificationSaveRequestDTO);
        return notificationResponseConverter.toDTO(notificationRepository.save(entity));
    }

    @Override
    public String delete(NotificationDeleteRequestDTO notificationDeleteRequestDTO) {
        if (userRepository.findOneById(notificationDeleteRequestDTO.getUserId()) == null) {
            throw new DuplicateUsernameException("user_not_exists");
        }
        NotificationEntity entity = notificationDeleteRequestConverter.toEntity(notificationDeleteRequestDTO);
        notificationRepository.delete(entity);
        return "";
    }

    @Override
    public NotificationResponseDTO changeStatus(NotificationChangeStatusRequestDTO notificationChangeStatusRequestDTO) {
        if (userRepository.findOneById(notificationChangeStatusRequestDTO.getUserId()) == null) {
            throw new DuplicateUsernameException("user_not_exists");
        }
        NotificationEntity entity = notificationChangeStatusRequestConverter.toEntity(notificationChangeStatusRequestDTO);
        return notificationResponseConverter.toDTO(notificationRepository.save(entity));
    }
}
