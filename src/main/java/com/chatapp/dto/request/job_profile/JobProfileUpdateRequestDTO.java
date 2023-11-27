package com.chatapp.dto.request.job_profile;

import lombok.Data;

@Data
public class JobProfileUpdateRequestDTO {
    private Long profileId;
    private String cvUrl;
    private String status;
}
