package com.chatapp.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "posts")
public class PostEntity extends BaseEntity {

    @Column(name = "status", nullable = false)
    private Byte status;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "active", nullable = false)
    private Byte active;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @OneToOne(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private NormalPostEntity normalPost;

    @OneToOne(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private RecruitmentPostEntity recruitmentPost;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<QuestionEntity> questions = new ArrayList<>();;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PostCommentEntity> comments = new ArrayList<>();;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PostLikeEntity> likes = new ArrayList<>();;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PostImageEntity> images = new ArrayList<>();;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<JobProfileEntity> jobProfiles = new ArrayList<>();;

    @ManyToMany(mappedBy = "postSave", fetch = FetchType.LAZY)
    private List<UserEntity> users = new ArrayList<>();;
}