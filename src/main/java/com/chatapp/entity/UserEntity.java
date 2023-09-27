package com.chatapp.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "fullname", nullable = false)
    private String fullName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "status", nullable = false)
    private Byte status;

    @Column(name = "image", nullable = false)
    private String image;

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<MessageEntity> receivedMessages = new HashSet<>();

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<MessageEntity> sentMessages = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", nullable = false), inverseJoinColumns = @JoinColumn(name = "role_id", nullable = false))
    private List<RoleEntity> roles;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_post", joinColumns = @JoinColumn(name = "user_id", nullable = false), inverseJoinColumns = @JoinColumn(name = "post_id", nullable = false))
    private List<PostEntity> postSave;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PostEntity> posts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ShortAnswerEntity> shortAnswers;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PostCommentEntity> comments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PostLikeEntity> postLikes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<NotificationEntity> notifications;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<FollowEntity> followUsers;

    @OneToMany(mappedBy = "userFollow", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<FollowEntity> followByUsers;

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ConversationEntity> receivedConversation;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ConversationEntity> sentConversation;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private BusinessesInfoEntity businessesInfos;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private StudentInfoEntity studentInfo;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private FacultyInfoEntity falcutyInfo;
}
