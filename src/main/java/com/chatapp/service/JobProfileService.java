package com.chatapp.service;

import java.util.List;

import com.chatapp.dto.request.JobApplyProfileRequestDTO;
import com.chatapp.dto.request.JobProfileUpdateRequestDTO;
import com.chatapp.dto.response.JobProfileResponseDTO;

public interface JobProfileService {
    String applyJobProfile(JobApplyProfileRequestDTO jobApplyProfileRequestDTO);
    List<JobProfileResponseDTO> getJobprofileByPostId(Long postId);

    boolean updateJobProfile(JobProfileUpdateRequestDTO jobProfileUpdateRequest);

    List<JobProfileResponseDTO> getJobprofileByUserId(Long userId);
    JobProfileResponseDTO getJobProfileDetail(Long jobId);
    boolean deleteById(Long profileId);
}
