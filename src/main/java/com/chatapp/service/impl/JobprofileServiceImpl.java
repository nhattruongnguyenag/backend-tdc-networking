package com.chatapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatapp.converter.request.JobApplyProfileRequestConverter;
import com.chatapp.dto.request.JobApplyProfileRequestDTO;
import com.chatapp.dto.response.SurveyResponeDTO;
import com.chatapp.entity.JobProfileEntity;
import com.chatapp.entity.PostEntity;
import com.chatapp.enums.PostType;
import com.chatapp.repository.JobProfileRepository;
import com.chatapp.repository.PostRepository;
import com.chatapp.repository.UserRepository;
import com.chatapp.service.JobProfileService;

@Service
public class JobprofileServiceImpl implements JobProfileService{

    @Autowired
    JobApplyProfileRequestConverter jobApplyProfileRequestConverter;

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
        JobProfileEntity jobProfileEntity = jobApplyProfileRequestConverter.toEntity(jobApplyProfileRequestDTO);
        jobProfileRepository.save(jobProfileEntity);
        return "";
    }
    
}
