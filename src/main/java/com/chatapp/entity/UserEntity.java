package com.chatapp.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "refresh_token", nullable = true)
    private String refreshToken;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "status", nullable = false)
    private Byte status;

    @Column(name = "image", nullable = true)
    private String image;

    @Column(name = "code", nullable = true, unique = true)
    private String code;

    @Column(name = "is_typing", nullable = true, columnDefinition = "boolean default false")
    private Boolean isTyping = false;

    @Column(name = "is_message_connect", nullable = true, columnDefinition = "boolean default false")
    private Boolean isMessageConnect = false;

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<MessageEntity> receivedMessages = new HashSet<>();

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<MessageEntity> sentMessages = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", nullable = false), inverseJoinColumns = @JoinColumn(name = "role_id", nullable = false))
    private List<RoleEntity> roles = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_save_posts", joinColumns = @JoinColumn(name = "user_id", nullable = false), inverseJoinColumns = @JoinColumn(name = "post_id", nullable = false))
    private List<PostEntity> postSave = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PostEntity> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ShortAnswerEntity> shortAnswers = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PostCommentEntity> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PostLikeEntity> postLikes = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<NotificationEntity> notifications = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<FollowEntity> followUsers = new ArrayList<>();

    @OneToMany(mappedBy = "userFollow", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<FollowEntity> followByUsers = new ArrayList<>();

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ConversationEntity> receivedConversation = new ArrayList<>();

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ConversationEntity> sentConversation = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private BusinessesInfoEntity businessesInfos;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private StudentInfoEntity studentInfo;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private FacultyInfoEntity falcutyInfo;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<JobProfileEntity> jobProfiles = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<DeviceTokenEntity> deviceTokens = new ArrayList<>();

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getTyping() {
        return isTyping;
    }

    public void setTyping(Boolean typing) {
        isTyping = typing;
    }

    public Boolean getMessageConnect() {
        return isMessageConnect;
    }

    public void setMessageConnect(Boolean messageConnect) {
        isMessageConnect = messageConnect;
    }

    public Set<MessageEntity> getReceivedMessages() {
        return receivedMessages;
    }

    public void setReceivedMessages(Set<MessageEntity> receivedMessages) {
        this.receivedMessages = receivedMessages;
    }

    public Set<MessageEntity> getSentMessages() {
        return sentMessages;
    }

    public void setSentMessages(Set<MessageEntity> sentMessages) {
        this.sentMessages = sentMessages;
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }

    public List<PostEntity> getPostSave() {
        return postSave;
    }

    public void setPostSave(List<PostEntity> postSave) {
        this.postSave = postSave;
    }

    public List<PostEntity> getPosts() {
        return posts;
    }

    public void setPosts(List<PostEntity> posts) {
        this.posts = posts;
    }

    public List<ShortAnswerEntity> getShortAnswers() {
        return shortAnswers;
    }

    public void setShortAnswers(List<ShortAnswerEntity> shortAnswers) {
        this.shortAnswers = shortAnswers;
    }

    public List<PostCommentEntity> getComments() {
        return comments;
    }

    public void setComments(List<PostCommentEntity> comments) {
        this.comments = comments;
    }

    public List<PostLikeEntity> getPostLikes() {
        return postLikes;
    }

    public void setPostLikes(List<PostLikeEntity> postLikes) {
        this.postLikes = postLikes;
    }

    public List<NotificationEntity> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<NotificationEntity> notifications) {
        this.notifications = notifications;
    }

    public List<FollowEntity> getFollowUsers() {
        return followUsers;
    }

    public void setFollowUsers(List<FollowEntity> followUsers) {
        this.followUsers = followUsers;
    }

    public List<FollowEntity> getFollowByUsers() {
        return followByUsers;
    }

    public void setFollowByUsers(List<FollowEntity> followByUsers) {
        this.followByUsers = followByUsers;
    }

    public List<ConversationEntity> getReceivedConversation() {
        return receivedConversation;
    }

    public void setReceivedConversation(List<ConversationEntity> receivedConversation) {
        this.receivedConversation = receivedConversation;
    }

    public List<ConversationEntity> getSentConversation() {
        return sentConversation;
    }

    public void setSentConversation(List<ConversationEntity> sentConversation) {
        this.sentConversation = sentConversation;
    }

    public BusinessesInfoEntity getBusinessesInfos() {
        return businessesInfos;
    }

    public void setBusinessesInfos(BusinessesInfoEntity businessesInfos) {
        this.businessesInfos = businessesInfos;
    }

    public StudentInfoEntity getStudentInfo() {
        return studentInfo;
    }

    public void setStudentInfo(StudentInfoEntity studentInfo) {
        this.studentInfo = studentInfo;
    }

    public FacultyInfoEntity getFalcutyInfo() {
        return falcutyInfo;
    }

    public void setFalcutyInfo(FacultyInfoEntity falcutyInfo) {
        this.falcutyInfo = falcutyInfo;
    }

    public List<JobProfileEntity> getJobProfiles() {
        return jobProfiles;
    }

    public void setJobProfiles(List<JobProfileEntity> jobProfiles) {
        this.jobProfiles = jobProfiles;
    }

    public List<DeviceTokenEntity> getDeviceTokens() {
        return deviceTokens;
    }

    public void setDeviceTokens(List<DeviceTokenEntity> deviceTokens) {
        this.deviceTokens = deviceTokens;
    }

    public boolean isTyping() {
        return isTyping;
    }
}
