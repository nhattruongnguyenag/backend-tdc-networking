package com.chatapp.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
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

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<MessageEntity> receivedMessages = new HashSet<>();

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<MessageEntity> sentMessages = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", nullable = false), inverseJoinColumns = @JoinColumn(name = "role_id", nullable = false))
    private List<RoleEntity> roles = new ArrayList<>();;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_save_posts", joinColumns = @JoinColumn(name = "user_id", nullable = false), inverseJoinColumns = @JoinColumn(name = "post_id", nullable = false))
    private List<PostEntity> postSave = new ArrayList<>();;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PostEntity> posts = new ArrayList<>();;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ShortAnswerEntity> shortAnswers = new ArrayList<>();;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PostCommentEntity> comments = new ArrayList<>();;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PostLikeEntity> postLikes = new ArrayList<>();;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<NotificationEntity> notifications = new ArrayList<>();;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<FollowEntity> followUsers = new ArrayList<>();;

    @OneToMany(mappedBy = "userFollow", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<FollowEntity> followByUsers = new ArrayList<>();;

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ConversationEntity> receivedConversation = new ArrayList<>();;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ConversationEntity> sentConversation = new ArrayList<>();;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private BusinessesInfoEntity businessesInfos;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private StudentInfoEntity studentInfo;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private FacultyInfoEntity falcutyInfo;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<JobProfileEntity> jobProfiles = new ArrayList<>();;
}
