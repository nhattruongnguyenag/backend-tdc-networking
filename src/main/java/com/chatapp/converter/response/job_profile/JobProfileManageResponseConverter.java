package com.chatapp.converter.response.job_profile;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.job_profile.JobProfilePendingResponseDTO;
import com.chatapp.entity.JobProfileEntity;

import org.springframework.stereotype.Component;

@Component
public class JobProfileManageResponseConverter extends BaseConverter<JobProfileEntity, JobProfilePendingResponseDTO>{

    @Override
    public JobProfilePendingResponseDTO toDTO(JobProfileEntity entity) {
        JobProfilePendingResponseDTO dto = super.toDTO(entity);
        dto.setJobTitle(entity.getPost().getRecruitmentPost().getTitle());
        dto.setCvUrl(entity.getCvUrl());
        dto.setCompanyAvatar(entity.getPost().getUser().getImage());
        dto.setCompanyName(entity.getPost().getUser().getName());
        return dto;
    }
}
