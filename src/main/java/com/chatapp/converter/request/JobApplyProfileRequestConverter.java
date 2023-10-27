package com.chatapp.converter.request;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.request.JobApplyProfileRequestDTO;
import com.chatapp.entity.JobProfileEntity;
import com.chatapp.repository.PostRepository;
import com.chatapp.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobApplyProfileRequestConverter extends BaseConverter<JobProfileEntity, JobApplyProfileRequestDTO>{

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public JobProfileEntity toEntity(JobApplyProfileRequestDTO dto) {
        JobProfileEntity jobProfileEntity = super.toEntity(dto);
        jobProfileEntity.setPost(postRepository.findOneById(dto.getPost_id()));
        jobProfileEntity.setUser(userRepository.findOneById(dto.getUser_id()));
        jobProfileEntity.setCvUrl(dto.getCv_url());
        return jobProfileEntity;
    }
}
