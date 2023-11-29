package com.chatapp.converter.response.job_profile;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.job_profile.JobProfileResponseDTO;
import com.chatapp.entity.JobProfileEntity;

import org.springframework.stereotype.Component;

@Component
public class JobProfileResponseConverter extends BaseConverter<JobProfileEntity,JobProfileResponseDTO>{

    @Override
    public JobProfileResponseDTO toDTO(JobProfileEntity entity) {
        JobProfileResponseDTO dto = super.toDTO(entity);
        dto.setJobTitle(entity.getPost().getRecruitmentPost().getTitle());
        dto.setCvUrl(entity.getCvUrl());
        dto.setCompanyAvatar(entity.getPost().getUser().getImage());
        dto.setCompanyName(entity.getPost().getUser().getName());
        return dto;
    }
}
