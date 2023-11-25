package com.chatapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "job_apply_profiles")
public class JobProfileEntity extends BaseEntity {
    
    @Column(name = "cv_url", nullable = false)
    private String cvUrl;

    @Column(name = "status", nullable = false)
    private String status = "received";

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = true)
    private PostEntity post;

    public String getCvUrl() {
        return cvUrl;
    }

    public void setCvUrl(String cvUrl) {
        this.cvUrl = cvUrl;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public PostEntity getPost() {
        return post;
    }

    public void setPost(PostEntity post) {
        this.post = post;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}