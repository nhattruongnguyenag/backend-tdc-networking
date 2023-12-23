package com.chatapp.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "notifications")
public class NotificationEntity extends BaseEntity {

    @Column(name = "userInteracted", nullable = true)
    private Long userInteracted;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "status", nullable = false)
    private Byte status;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "data", nullable = true)
    private String data;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }


    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Long getUserInteracted() {
        return this.userInteracted;
    }

    public void setUserInteracted(Long userInteracted) {
        this.userInteracted = userInteracted;
    }
}
