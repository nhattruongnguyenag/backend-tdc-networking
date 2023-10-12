package com.chatapp.dto.request;


import java.util.List;

import lombok.Data;

@Data
public class RecruitmentPostUpdateOrSageRequestDTO {
    private Long id;
    private Long userId;
    private String type;
    private List<String> images;
    private String title;
    private Long salary;
    private String benefit;
    private String description;
    private String employmentType;
    private String expiration;
    private String location;
    private String requirement;
}
