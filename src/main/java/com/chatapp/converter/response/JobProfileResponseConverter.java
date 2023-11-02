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
        JobProfileResponseDTO jobProfileResponseDTO = super.toDTO(entity);
        jobProfileResponseDTO.setUser(userInfoResponseConverter.toDTO(entity.getUser()));
        jobProfileResponseDTO.setPost(postInfoResponseConverter.toDTO(entity.getPost()));
        return jobProfileResponseDTO;
    }
}
