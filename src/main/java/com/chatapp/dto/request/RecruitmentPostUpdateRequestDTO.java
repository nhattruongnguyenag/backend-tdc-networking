package com.chatapp.dto.request;

import lombok.Data;

@Data
public class RecruitmentPostUpdateRequestDTO {
    private Long id;
    private String title;
    private Long salary;
    private String benefit;
    private String description;
    private String employmentType;
    private String expiration;
    private String location;
    private String requirement;
}
