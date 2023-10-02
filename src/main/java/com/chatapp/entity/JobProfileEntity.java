package com.chatapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "job_apply_profiles")
public class JobProfileEntity extends BaseEntity {
    
    @Column(name = "cv_url", nullable = false)
    private String cvUrl;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = true)
    private PostEntity post;
}