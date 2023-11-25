package com.chatapp.dto.request;

import lombok.Data;

@Data
public class JobProfileUpdateRequestDTO {
    private Long profileId;
    private String cvUrl;
}
