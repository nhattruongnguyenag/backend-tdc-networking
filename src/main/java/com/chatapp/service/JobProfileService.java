package com.chatapp.service;

import java.util.List;

import com.chatapp.dto.request.job_profile.JobApplyProfileRequestDTO;
import com.chatapp.dto.request.job_profile.JobProfileUpdateRequestDTO;
import com.chatapp.dto.response.job_profile.JobProfileManageResponseDTO;
import com.chatapp.dto.response.job_profile.JobProfilePendingResponseDTO;

public interface JobProfileService {
    String applyJobProfile(JobApplyProfileRequestDTO jobApplyProfileRequestDTO);
    List<JobProfileManageResponseDTO> getJobprofileByPostId(Long postId);

    boolean updateJobProfile(JobProfileUpdateRequestDTO jobProfileUpdateRequest);

    List<JobProfilePendingResponseDTO> getJobprofileByUserId(Long userId);
    JobProfilePendingResponseDTO getJobProfileDetail(Long jobId);
    boolean deleteById(Long profileId);
}
