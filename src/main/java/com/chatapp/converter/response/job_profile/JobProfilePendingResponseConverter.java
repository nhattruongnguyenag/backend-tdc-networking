package com.chatapp.converter.response.job_profile;


import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.job_profile.JobProfileManageResponseDTO;
import com.chatapp.entity.JobProfileEntity;
import org.springframework.stereotype.Component;

@Component
public class JobProfilePendingResponseConverter  extends BaseConverter<JobProfileEntity, JobProfileManageResponseDTO> {
    @Override
    public JobProfileManageResponseDTO toDTO(JobProfileEntity jobProfileEntity) {
        JobProfileManageResponseDTO jobProfileManageResponse = super.toDTO(jobProfileEntity);
        jobProfileManageResponse.setStudentName(jobProfileEntity.getUser().getName());
        jobProfileManageResponse.setJobTitle(jobProfileEntity.getPost().getRecruitmentPost().getTitle());
        jobProfileManageResponse.setPhone(jobProfileEntity.getUser().getPhone());
        jobProfileManageResponse.setEmail(jobProfileEntity.getUser().getEmail());
        jobProfileManageResponse.setStudentAvatar(jobProfileEntity.getUser().getImage());
        return jobProfileManageResponse;
    }
}
