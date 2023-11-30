package com.chatapp.dto.response.job_profile;

import lombok.Data;

@Data
public class JobProfileManageResponseDTO {
    private Long id;
    private String studentName;
    private String jobTitle;
    private String createdAt;
    private String studentAvatar;
    private String phone;
    private String email;
    private String cvUrl;
    private String status;
}
