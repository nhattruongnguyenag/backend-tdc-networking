package com.chatapp.dto.response.user;


import com.chatapp.dto.BaseDTO;
import com.chatapp.dto.response.user.follow.UserFollowResponseDTO;

import java.util.List;

public class UserInfoResponseDTO extends BaseDTO {
    private String email;
    private String name;
    private String image;
    private String background;
    private String phone;
    private Byte status;
    private String code;
    private Byte isTyping;
    private Byte isMessageConnect;
    private String roleCodes;
    private List<UserFollowResponseDTO> follows;
    private String lastActive;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Byte getIsTyping() {
        return isTyping;
    }

    public void setIsTyping(Byte isTyping) {
        this.isTyping = isTyping;
    }

    public Byte getIsMessageConnect() {
        return isMessageConnect;
    }

    public void setIsMessageConnect(Byte isMessageConnect) {
        this.isMessageConnect = isMessageConnect;
    }

    public String getRoleCodes() {
        return roleCodes;
    }

    public void setRoleCodes(String roleCodes) {
        this.roleCodes = roleCodes;
    }

    public List<UserFollowResponseDTO> getFollows() {
        return follows;
    }

    public void setFollows(List<UserFollowResponseDTO> follows) {
        this.follows = follows;
    }

    public String getLastActive() {
        return lastActive;
    }

    public void setLastActive(String lastActive) {
        this.lastActive = lastActive;
    }
}
