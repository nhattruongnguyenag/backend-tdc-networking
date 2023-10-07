package com.chatapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "follows")
public class FollowEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "follow_user_id", nullable = false)
    private UserEntity userFollow;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public UserEntity getUserFollow() {
        return userFollow;
    }

    public void setUserFollow(UserEntity userFollow) {
        this.userFollow = userFollow;
    }
}