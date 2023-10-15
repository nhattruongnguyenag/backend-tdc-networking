package com.chatapp.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "notifications")
public class NotificationEntity extends BaseEntity {

    @Column(name = "content", nullable = false, length = 1024)
    private String content;

    @Column(name = "status", nullable = false)
    private Byte status;

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
}
