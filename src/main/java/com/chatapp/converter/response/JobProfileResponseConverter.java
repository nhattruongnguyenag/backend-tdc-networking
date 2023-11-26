package com.chatapp.converter.response;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.JobProfileResponseDTO;
import com.chatapp.entity.JobProfileEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobProfileResponseConverter extends BaseConverter<JobProfileEntity,JobProfileResponseDTO>{

    @Autowired
    private UserInfoResponseConverter userInfoResponseConverter;
     @Autowired
    private PostInfoResponseConverter postInfoResponseConverter;

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
