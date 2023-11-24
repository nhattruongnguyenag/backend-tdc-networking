package com.chatapp.dto.response;
import lombok.Data;

@Data
public class RecruitmentPostResponseDTO extends PostInfoResponseDTO {
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
