package com.chatapp.entity.identity;

import java.io.Serializable;

import com.chatapp.entity.UserEntity;

public class OptionUserId implements Serializable {
    private UserEntity user;
    private String optionKey;


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

}
