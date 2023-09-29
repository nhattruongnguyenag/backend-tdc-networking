package com.chatapp.entity;


import java.sql.Timestamp;
import java.util.Date;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "recruitment_posts")
public class RecruitmentPostEntity extends BaseEntity {
    
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
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
}