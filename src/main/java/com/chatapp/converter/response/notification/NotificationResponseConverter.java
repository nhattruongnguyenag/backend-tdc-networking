package com.chatapp.converter.response.notification;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.converter.response.job_profile.JobProfilePendingResponseConverter;
import com.chatapp.converter.response.post.PostSearchResponseConverter;
import com.chatapp.converter.response.post.log.PostRejectLogResponseConverter;
import com.chatapp.converter.response.user.UserInfoResponseConverter;
import com.chatapp.dto.response.job_profile.JobProfileManageResponseDTO;
import com.chatapp.dto.response.notification.NotificationResponseDTO;
import com.chatapp.dto.response.post.PostSearchResponseDTO;
import com.chatapp.dto.response.post.log.PostRejectLogResponseDTO;
import com.chatapp.entity.JobProfileEntity;
import com.chatapp.entity.NotificationEntity;
import com.chatapp.entity.PostApprovalLogEntity;
import com.chatapp.entity.PostEntity;
import com.chatapp.enums.Notification;
import com.chatapp.repository.JobProfileRepository;
import com.chatapp.repository.PostApprovalLogRepository;
import com.chatapp.repository.PostRepository;
import com.chatapp.repository.UserRepository;

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
    private UserRepository userRepository;

    @Autowired
    private PostApprovalLogRepository postApprovalLogRepository;

    @Autowired
    private PostSearchResponseConverter postSearchResponseConverter;

    @Autowired
    private PostRejectLogResponseConverter postRejectLogResponseConverter;

    @Override
    public NotificationResponseDTO toDTO(NotificationEntity entity) {
        NotificationResponseDTO notificationResponseDTO = super.toDTO(entity);
        if (entity.getUserInteracted() != null) {
            notificationResponseDTO.setUserInteracted(
                    userInfoResponseConverter.toDTO(userRepository.findOneById(entity.getUserInteracted())));
        }
        if (entity.getData() != null && !entity.getData().equals("")) {
            String id = entity.getData().split(":")[1];
            if (entity.getType().equals(Notification.USER_APPLY_JOB.getValue())) {
                if (jobProfileRepository.findOneById(Long.valueOf(id)) != null) {
                    JobProfileEntity jobProfileEntity = jobProfileRepository.findOneById(Long.valueOf(id));
                    JobProfileManageResponseDTO jobProfileManageResponseDTO = jobProfilePendingResponseConverter
                            .toDTO(jobProfileEntity);
                    notificationResponseDTO.setDataValue(jobProfileManageResponseDTO);
                } else {
                    notificationResponseDTO.setDataValue(null);
                }
            } else if (entity.getType().equals(Notification.POST_LOG.getValue())) {
                if (postApprovalLogRepository
                        .findOneById(Long.valueOf(id)) != null) {
                    PostApprovalLogEntity postApprovalLogEntity = postApprovalLogRepository
                            .findOneById(Long.valueOf(id));
                    PostRejectLogResponseDTO postRejectLogResponseDTO = postRejectLogResponseConverter.toDTO(postApprovalLogEntity);
                    notificationResponseDTO.setDataValue(postRejectLogResponseDTO);
                } else {
                    notificationResponseDTO.setDataValue(null);
                }

            } else {
                if (postRepository.findOneById(Long.valueOf(id)) != null) {
                    PostEntity postEntity = postRepository.findOneById(Long.valueOf(id));
                    postEntity.setUserLogin(entity.getUser().getId());
                    PostSearchResponseDTO postSearchResponseDTO = postSearchResponseConverter.toDTO(postEntity);
                    notificationResponseDTO.setDataValue(postSearchResponseDTO);
                } else {
                    notificationResponseDTO.setDataValue(null);
                }
            }
        }
        return notificationResponseDTO;
    }
}
