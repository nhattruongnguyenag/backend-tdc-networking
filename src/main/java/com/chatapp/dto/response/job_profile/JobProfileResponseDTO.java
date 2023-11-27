package com.chatapp.dto.response.job_profile;

import com.chatapp.dto.BaseDTO;
import com.chatapp.dto.response.post.PostSearchResponseDTO;

import lombok.Data;

@Data
public class JobProfileResponseDTO {
    private Long id;
    private String companyName;
    private String jobTitle;
    private String createdAt;
    private String companyAvatar;
    private String cvUrl;
    private String status;
}
