package com.chatapp.service;

import java.util.List;

import com.chatapp.dto.request.JobApplyProfileRequestDTO;
import com.chatapp.dto.response.JobProfileResponseDTO;

public interface JobProfileService {
    String applyJobProfile(JobApplyProfileRequestDTO jobApplyProfileRequestDTO);
    List<JobProfileResponseDTO> getJobprofileByPostId(Long postId);
    JobProfileResponseDTO getJobProfileDetail(Long postId , Long jobId);
}
