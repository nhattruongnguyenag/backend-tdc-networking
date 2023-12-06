package com.chatapp.entity;

import com.chatapp.entity.identity.OptionUserId;

import jakarta.persistence.*;

@Entity
@Table(name = "option_user")
@IdClass(OptionUserId.class)
public class OptionUserEntity{

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Id
    @Column(name = "option_key", nullable = false)
    private String optionKey;

    @Column(name = "value", nullable = false)
    private String value;

    public UserEntity getUser() {
        return this.user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getOptionKey() {
        return this.optionKey;
    }

    public void setOptionKey(String optionKey) {
        this.optionKey = optionKey;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}