package com.chatapp.converter.request.job_profile;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.converter.response.user.UserInfoResponseConverter;
import com.chatapp.dto.request.job_profile.JobApplyProfileRequestDTO;
import com.chatapp.entity.JobProfileEntity;
import com.chatapp.enums.Role;
import com.chatapp.repository.JobProfileRepository;
import com.chatapp.repository.PostRepository;
import com.chatapp.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobApplyProfileRequestConverter extends BaseConverter<JobProfileEntity, JobApplyProfileRequestDTO> {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobProfileRepository jobProfileRepository;

    @Autowired
    private UserInfoResponseConverter userInfoResponseConverter;

    @Override
    public JobProfileEntity toEntity(JobApplyProfileRequestDTO dto) {
        if (userInfoResponseConverter.toDTO(userRepository.findOneById(dto.getUser_id())).getRoleCodes()
                .contains(Role.STUDENT.getName())) {
            JobProfileEntity jobProfileEntity = super.toEntity(dto);
            jobProfileEntity.setPost(postRepository.findOneById(dto.getPost_id()));
            jobProfileEntity.setUser(userRepository.findOneById(dto.getUser_id()));
            jobProfileEntity.setCvUrl(dto.getCv_url());
            return jobProfileEntity;
        } else {
            throw new RuntimeException("only_student_can_apply_job_profile");
        }
    }

    public JobProfileEntity toUpdateEntity(JobApplyProfileRequestDTO dto) {
        if (userInfoResponseConverter.toDTO(userRepository.findOneById(dto.getUser_id())).getRoleCodes()
                .contains(Role.STUDENT.getName())) {
            JobProfileEntity jobProfileEntity = jobProfileRepository.findOneByPost_IdAndUser_Id(dto.getPost_id(),
                    dto.getUser_id());
            jobProfileEntity.setCvUrl(dto.getCv_url());
            return jobProfileEntity;
        } else {
            throw new RuntimeException("only_student_can_apply_job_profile");
        }
    }
}
