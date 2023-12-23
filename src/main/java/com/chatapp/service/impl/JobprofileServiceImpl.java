package com.chatapp.service.impl;

import java.util.List;

import com.chatapp.converter.response.job_profile.JobProfilePendingResponseConverter;
import com.chatapp.dto.request.job_profile.JobApplyProfileRequestDTO;
import com.chatapp.dto.request.job_profile.JobProfileUpdateRequestDTO;
import com.chatapp.dto.response.job_profile.JobProfileManageResponseDTO;
import com.chatapp.dto.response.job_profile.JobProfilePendingResponseDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatapp.converter.request.job_profile.JobApplyProfileRequestConverter;
import com.chatapp.converter.response.job_profile.JobProfileManageResponseConverter;
import com.chatapp.converter.response.user.UserInfoResponseConverter;
import com.chatapp.entity.JobProfileEntity;
import com.chatapp.entity.PostEntity;
import com.chatapp.entity.UserEntity;
import com.chatapp.enums.Notification;
import com.chatapp.enums.PostType;
import com.chatapp.enums.Role;
import com.chatapp.repository.JobProfileRepository;
import com.chatapp.repository.PostRepository;
import com.chatapp.repository.UserRepository;
import com.chatapp.service.JobProfileService;
import com.chatapp.service.NotificationService;

@Service
public class JobprofileServiceImpl implements JobProfileService {
    @Autowired
    JobApplyProfileRequestConverter jobApplyProfileRequestConverter;
    @Autowired
    private JobProfilePendingResponseConverter jobProfileManageResponseConverter;
    @Autowired
    JobProfileManageResponseConverter jobProfileResponseConverter;
    @Autowired
    UserInfoResponseConverter userInfoResponseConverter;
    @Autowired
    private NotificationService notificationService;

    @Autowired
    JobProfileRepository jobProfileRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public String applyJobProfile(JobApplyProfileRequestDTO jobApplyProfileRequestDTO) {
        if (postRepository.findOneById(jobApplyProfileRequestDTO.getPost_id()) == null) {
            throw new RuntimeException("this_post_does_not_exist");
        }

        PostEntity postEntity = postRepository.findOneById(jobApplyProfileRequestDTO.getPost_id());
        if (!postEntity.getType().equals(PostType.RECRUIMENT.getName())) {
            throw new RuntimeException("this_post_is_not_a_recruitment");
        }

        if (userRepository.findOneById(jobApplyProfileRequestDTO.getUser_id()) == null) {
            throw new RuntimeException("user_is_not_exist");
        }

        JobProfileEntity jobProfileEntity = null;

        if (jobProfileRepository.findOneByPost_IdAndUser_Id(jobApplyProfileRequestDTO.getPost_id(),
                jobApplyProfileRequestDTO.getUser_id()) != null) {
            jobProfileEntity = jobApplyProfileRequestConverter
                    .toUpdateEntity(jobApplyProfileRequestDTO);
            jobProfileRepository.save(jobProfileEntity);
        } else {
            jobProfileEntity = jobApplyProfileRequestConverter.toEntity(jobApplyProfileRequestDTO);
            jobProfileRepository.save(jobProfileEntity);

        }
        notificationService.addNotification(Notification.USER_CREATE_POST_WATCH_APPLY_JOB.getValue(),
                Notification.USER_CREATE_POST_WATCH_APPLY_JOB.getValue(), jobProfileEntity.getPost().getUser().getId(),
                "jobId:" + jobProfileEntity.getPost().getId(),jobApplyProfileRequestDTO.getUser_id());
        notificationService.addNotification(Notification.USER_APPLY_JOB.getValue(),
                Notification.USER_APPLY_JOB.getValue(), jobApplyProfileRequestDTO.getUser_id(),
                "jobId:" + jobProfileEntity.getId(),null);
        return "";
    }

    @Override
    public List<JobProfileManageResponseDTO> getJobprofileByPostId(Long postId) {
        if (postRepository.findOneById(postId) == null) {
            throw new RuntimeException("this_post_does_not_exist");
        }
        PostEntity postEntity = postRepository.findOneById(postId);
        if (!postEntity.getType().equals(PostType.RECRUIMENT.getName())) {
            throw new RuntimeException("this_post_is_not_a_recruitment");
        }
        List<JobProfileManageResponseDTO> jobProfileResponseDTOs = jobProfileManageResponseConverter
                .toDTOGroup(jobProfileRepository.findAllByPost_Id(postId));
        return jobProfileResponseDTOs;
    }

    @Override
    public JobProfilePendingResponseDTO getJobProfileDetail(Long jobId) {
        JobProfilePendingResponseDTO jobProfileResponseDTO = jobProfileResponseConverter
                .toDTO(jobProfileRepository.findOneById(jobId));
        return jobProfileResponseDTO;
    }

    @Override
    public boolean deleteById(Long profileId) {
        if (jobProfileRepository.findOneById(profileId) != null) {
            jobProfileRepository.deleteById(profileId);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateJobProfile(JobProfileUpdateRequestDTO jobProfileUpdateRequest) {
        JobProfileEntity jobProfileEntity = jobProfileRepository.findOneById(jobProfileUpdateRequest.getProfileId());
        if (jobProfileEntity != null) {
            if (jobProfileUpdateRequest.getCvUrl() != null) {
                jobProfileEntity.setCvUrl(jobProfileUpdateRequest.getCvUrl());
            }

            if (jobProfileUpdateRequest.getStatus() != null) {
                jobProfileEntity.setStatus(jobProfileUpdateRequest.getStatus());
            }
            return jobProfileRepository.save(jobProfileEntity) != null;
        }
        return false;
    }

    @Override
    public List<JobProfilePendingResponseDTO> getJobprofileByUserId(Long userId) {
        if (userRepository.findOneById(userId) == null) {
            throw new RuntimeException("this_user_does_not_exist");
        }
        if (userInfoResponseConverter.toDTO(userRepository.findOneById(userId)).getRoleCodes()
                .contains(Role.STUDENT.getName())) {
            UserEntity entity = userRepository.findOneById(userId);
            List<JobProfilePendingResponseDTO> jobProfileResponseDTOs = jobProfileResponseConverter
                    .toDTOGroup(entity.getJobProfiles());
            return jobProfileResponseDTOs;
        } else {
            throw new RuntimeException("only_student_can_see_list_job_profile");
        }
    }
}
