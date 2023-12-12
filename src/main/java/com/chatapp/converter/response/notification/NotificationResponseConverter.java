package com.chatapp.converter.response.notification;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.converter.response.job_profile.JobProfileManageResponseConverter;
import com.chatapp.converter.response.job_profile.JobProfilePendingResponseConverter;
import com.chatapp.converter.response.post.PostSearchResponseConverter;
import com.chatapp.converter.response.user.UserInfoResponseConverter;
import com.chatapp.dto.response.job_profile.JobProfileManageResponseDTO;
import com.chatapp.dto.response.notification.NotificationResponseDTO;
import com.chatapp.dto.response.post.PostSearchResponseDTO;
import com.chatapp.entity.JobProfileEntity;
import com.chatapp.entity.NotificationEntity;
import com.chatapp.entity.PostEntity;
import com.chatapp.enums.Notification;
import com.chatapp.repository.JobProfileRepository;
import com.chatapp.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationResponseConverter extends BaseConverter<NotificationEntity, NotificationResponseDTO> {

    @Autowired
    private UserInfoResponseConverter userInfoResponseConverter;

    @Autowired
    private JobProfilePendingResponseConverter jobProfilePendingResponseConverter;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private JobProfileRepository jobProfileRepository;

    @Autowired
    private PostSearchResponseConverter postSearchResponseConverter;

    @Override
    public NotificationResponseDTO toDTO(NotificationEntity entity) {
        NotificationResponseDTO notificationResponseDTO = super.toDTO(entity);
        notificationResponseDTO.setUser(userInfoResponseConverter.toDTO(entity.getUser()));
        if (entity.getData() != null && entity.getData() != "") {
            String id = entity.getData().split(":")[1];
            if (!entity.getType().equals(Notification.USER_APPLY_JOB.getValue())) {
                PostEntity postEntity = postRepository.findOneById(Long.valueOf(id));
                postEntity.setUserLogin(entity.getUser().getId());
                PostSearchResponseDTO postSearchResponseDTO = postSearchResponseConverter.toDTO(postEntity);
                notificationResponseDTO.setDataValue(postSearchResponseDTO);
            }
            else{
                JobProfileEntity jobProfileEntity = jobProfileRepository.findOneById(Long.valueOf(id));
                JobProfileManageResponseDTO jobProfileManageResponseDTO = jobProfilePendingResponseConverter.toDTO(jobProfileEntity);
                notificationResponseDTO.setDataValue(jobProfileManageResponseDTO);
            }
        }
        return notificationResponseDTO;
    }
}
