package com.chatapp.service.impl;

import java.util.List;

import com.chatapp.converter.request.notification.NotificationChangeAllStatusByUserIdRequest;
import com.chatapp.converter.request.notification.NotificationChangeStatusRequestConverter;
import com.chatapp.converter.request.notification.NotificationDeleteRequestConverter;
import com.chatapp.converter.request.notification.NotificationSaveRequestConverter;
import com.chatapp.converter.response.notification.NotificationResponseConverter;
import com.chatapp.dto.request.notification.NotificationByUserRequestDTO;
import com.chatapp.dto.request.notification.NotificationChangeStatusRequestDTO;
import com.chatapp.dto.request.notification.NotificationDeleteRequestDTO;
import com.chatapp.dto.request.notification.NotificationSaveRequestDTO;
import com.chatapp.dto.response.notification.NotificationResponseDTO;
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
    public List<NotificationResponseDTO> findById(NotificationByUserRequestDTO notificationByUserRequestDTO) {
        if (userRepository.findOneById(notificationByUserRequestDTO.getId()) == null) {
            throw new DuplicateUsernameException("user_not_exists");
        }
        return notificationResponseConverter
                .toDTOGroup(
                        notificationRepository.findByUser_IdOrderByUpdatedAtDesc(notificationByUserRequestDTO.getId()));
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
        NotificationEntity entity = notificationChangeStatusRequestConverter
                .toEntity(notificationChangeStatusRequestDTO);
        entity.setStatus((byte) 1);
        return notificationResponseConverter.toDTO(notificationRepository.save(entity));
    }

    @Override
    public String changeStatusAll(
            NotificationChangeAllStatusByUserIdRequest notificationChangeAllStatusByUserIdRequest) {
        if (userRepository.findOneById(notificationChangeAllStatusByUserIdRequest.getUserId()) == null) {
            throw new DuplicateUsernameException("user_not_exists");
        }
        List<NotificationEntity> entities = notificationRepository
                .findByUser_IdOrderByUpdatedAtDesc(notificationChangeAllStatusByUserIdRequest.getUserId());
        for (NotificationEntity entity : entities) {
            entity.setStatus((byte) 1);
            notificationRepository.save(entity);
        }
        return "";
    }

    @Override
    public NotificationResponseDTO makeNotSeen(NotificationChangeStatusRequestDTO notificationChangeStatusRequestDTO) {
        if (userRepository.findOneById(notificationChangeStatusRequestDTO.getUserId()) == null) {
            throw new DuplicateUsernameException("user_not_exists");
        }
        NotificationEntity entity = notificationChangeStatusRequestConverter
                .toEntity(notificationChangeStatusRequestDTO);
        entity.setStatus((byte) 0);
        return notificationResponseConverter.toDTO(notificationRepository.save(entity));
    }

    @Override
    public List<NotificationResponseDTO> findByContent(String content) {
        return notificationResponseConverter.toDTOGroup(notificationRepository.findByContentContains(content));
    }

    @Override
    public void addNotification(String content, String type, Long userId, String data, Long userInteracted) {
        NotificationSaveRequestDTO notificationSaveRequestDTO = new NotificationSaveRequestDTO();
        notificationSaveRequestDTO.setContent(content);
        notificationSaveRequestDTO.setType(type);
        notificationSaveRequestDTO.setUserId(userId);
        notificationSaveRequestDTO.setData(data);
        if (userRepository.findOneById(notificationSaveRequestDTO.getUserId()) == null) {
            throw new DuplicateUsernameException("user_not_exists");
        }
        NotificationEntity entity = notificationSaveRequestConverter.toEntity(notificationSaveRequestDTO);
        entity.setUserInteracted(userInteracted);
        notificationRepository.save(entity);
    }

    @Override
    public Integer getCountNotification(NotificationByUserRequestDTO notificationByUserRequestDTO) {
        if (userRepository.findOneById(notificationByUserRequestDTO.getId()) == null) {
            throw new DuplicateUsernameException("user_not_exists");
        }
        return notificationRepository.findByUser_IdAndStatusOrderByUpdatedAtDesc(notificationByUserRequestDTO.getId(),Long.valueOf(0)).size();
    }
}
