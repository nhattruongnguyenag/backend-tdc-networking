package com.chatapp.entity;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "recruitment_posts")
public class RecruitmentPostEntity extends BaseEntity {
    
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false, length = 1024)
    private String description;

    @Column(name = "requirement", nullable = false)
    private String requirement;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "benefit", nullable = false)
    private String benefit;

    @Column(name = "salary", nullable = false)
    private Long salary;

    @Column(name = "expiration", nullable = false)
    private Date expiration;

    @Column(name = "employment_type", nullable = false)
    private String employmentType;

    @OneToOne
    @JoinColumn(name = "post_id", nullable = false)
    private PostEntity post;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBenefit() {
        return benefit;
    }

    public void setBenefit(String benefit) {
        this.benefit = benefit;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public PostEntity getPost() {
        return post;
    }

    public void setPost(PostEntity post) {
        this.post = post;
    }
}