package com.chatapp.dto.response.job_profile;


import lombok.Data;

@Data
public class JobProfilePendingResponseDTO {
    private Long id;
    private String companyName;
    private String jobTitle;
    private String createdAt;
    private String companyAvatar;
    private String cvUrl;
    private String status;
}
