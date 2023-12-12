package com.chatapp.converter.response.notification;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.converter.response.post.PostSearchResponseConverter;
import com.chatapp.converter.response.user.UserInfoResponseConverter;
import com.chatapp.dto.response.notification.NotificationResponseDTO;
import com.chatapp.dto.response.post.PostSearchResponseDTO;
import com.chatapp.entity.NotificationEntity;
import com.chatapp.entity.PostEntity;
import com.chatapp.enums.Notification;
import com.chatapp.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationResponseConverter extends BaseConverter<NotificationEntity, NotificationResponseDTO> {

    @Autowired
    private UserInfoResponseConverter userInfoResponseConverter;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostSearchResponseConverter postSearchResponseConverter;

    @Override
    public NotificationResponseDTO toDTO(NotificationEntity entity) {
        NotificationResponseDTO notificationResponseDTO = super.toDTO(entity);
        notificationResponseDTO.setUser(userInfoResponseConverter.toDTO(entity.getUser()));
        if (entity.getData() != null && entity.getData() != "") {
            if (!entity.getType().equals(Notification.USER_APPLY_JOB.getValue())) {
                String id = entity.getData().split(":")[1];
                PostEntity postEntity = postRepository.findOneById(Long.valueOf(1));
                postEntity.setUserLogin(entity.getUser().getId());
                PostSearchResponseDTO postSearchResponseDTO = postSearchResponseConverter.toDTO(postEntity);
                notificationResponseDTO.setDataValue(postSearchResponseDTO);
            }
            else{
                notificationResponseDTO.setDataValue(null);
            }
        }
        return notificationResponseDTO;
    }
}
