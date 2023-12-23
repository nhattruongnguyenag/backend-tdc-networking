package com.chatapp.dto.response.post.recruitment;

import com.chatapp.dto.response.post.PostInfoResponseDTO;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public String getBenefit() {
        return benefit;
    }

    public void setBenefit(String benefit) {
        this.benefit = benefit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public Long getIsApplyJob() {
        return isApplyJob;
    }

    public void setIsApplyJob(Long isApplyJob) {
        this.isApplyJob = isApplyJob;
    }
}
