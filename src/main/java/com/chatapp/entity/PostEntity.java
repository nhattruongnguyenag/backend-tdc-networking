package com.chatapp.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private GroupEntity group;

    @OneToOne(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private NormalPostEntity normalPost;

    @OneToOne(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private RecruitmentPostEntity recruitmentPost;

    @OneToOne(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private SurveyPostEntity surveyPost;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PostCommentEntity> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PostLikeEntity> likes = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PostImageEntity> images = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<JobProfileEntity> jobProfiles = new ArrayList<>();

    @ManyToMany(mappedBy = "postSave", fetch = FetchType.LAZY)
    private List<UserEntity> users = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "post_topic", joinColumns = @JoinColumn(name = "post_id", nullable = false), inverseJoinColumns = @JoinColumn(name = "topic_id", nullable = false))
    private List<TopicEntity> topics = new ArrayList<>();


    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<TopicEntity> getTopics() {
        return this.topics;
    }

    public void setTopics(List<TopicEntity> topics) {
        this.topics = topics;
    }

    public GroupEntity getGroup() {
        return this.group;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }

    public Byte getActive() {
        return active;
    }

    public void setActive(Byte active) {
        this.active = active;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public NormalPostEntity getNormalPost() {
        return normalPost;
    }

    public void setNormalPost(NormalPostEntity normalPost) {
        this.normalPost = normalPost;
    }

    public SurveyPostEntity getSurveyPost() {
        return surveyPost;
    }

    public void setSurveyPost(SurveyPostEntity surveyPost) {
        this.surveyPost = surveyPost;
    }

    public RecruitmentPostEntity getRecruitmentPost() {
        return recruitmentPost;
    }

    public void setRecruitmentPost(RecruitmentPostEntity recruitmentPost) {
        this.recruitmentPost = recruitmentPost;
    }

    public List<PostCommentEntity> getComments() {
        return comments;
    }

    public void setComments(List<PostCommentEntity> comments) {
        this.comments = comments;
    }

    public List<PostLikeEntity> getLikes() {
        return likes;
    }

    public void setLikes(List<PostLikeEntity> likes) {
        this.likes = likes;
    }

    public List<PostImageEntity> getImages() {
        return images;
    }

    public void setImages(List<PostImageEntity> images) {
        this.images = images;
    }

    public List<JobProfileEntity> getJobProfiles() {
        return jobProfiles;
    }

    public void setJobProfiles(List<JobProfileEntity> jobProfiles) {
        this.jobProfiles = jobProfiles;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }
}
