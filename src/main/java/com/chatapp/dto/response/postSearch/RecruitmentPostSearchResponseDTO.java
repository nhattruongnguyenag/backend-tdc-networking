package com.chatapp.dto.response.postSearch;

import lombok.Data;

@Data
public class RecruitmentPostSearchResponseDTO extends PostSearchResponseDTO {
    private String title;
    private Long salary;
    private String benefit;
    private String description;
    private String employmentType;
    private String expiration;
    private String location;
    private String requirement;
    private Long isApplyJob;
}
