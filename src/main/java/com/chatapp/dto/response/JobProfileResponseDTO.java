package com.chatapp.dto.response;

import com.chatapp.dto.BaseDTO;

import com.chatapp.dto.response.postSearch.PostSearchResponseDTO;
import lombok.Data;

@Data
public class JobProfileResponseDTO {
    private Long id;
    private String companyName;
    private String jobTitle;
    private String createdAt;
    private String companyAvatar;
    private String cvUrl;
}
