package com.chatapp.converter.request.user.post_save;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.request.user.post_save.UserSavePostRequestDTO;
import com.chatapp.entity.UserEntity;
import com.chatapp.enums.Notification;
import com.chatapp.repository.PostRepository;
import com.chatapp.repository.UserRepository;
import com.chatapp.service.NotificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserSavePostRequestConverter extends BaseConverter<UserEntity, UserSavePostRequestDTO> {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private NotificationService notificationService;

    @Override
    public UserEntity toEntity(UserSavePostRequestDTO dto) {
        UserEntity userEntity = userRepository.findOneById(dto.getUserId());
        if (userEntity.getPostSave().contains(postRepository.findOneById(dto.getPostId()))) {
            userEntity.getPostSave().remove(postRepository.findOneById(dto.getPostId()));
        } else {
            userEntity.getPostSave().add(postRepository.findOneById(dto.getPostId()));
            notificationService.addNotification(Notification.SAVE_POST.getValue(),
                Notification.SAVE_POST.getValue(), dto.getUserId(),
                "id:" + dto.getPostId(), null);
        }
        return userEntity;
    }
}
