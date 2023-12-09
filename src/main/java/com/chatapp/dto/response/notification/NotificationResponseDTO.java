package com.chatapp.dto.response.notification;

import com.chatapp.dto.BaseDTO;
import com.chatapp.dto.response.user.UserInfoResponseDTO;

public class NotificationResponseDTO extends BaseDTO{
    private String content;
    private String status;
    private String type;
    private String data;
    private UserInfoResponseDTO user;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public UserInfoResponseDTO getUser() {
        return user;
    }

    public void setUser(UserInfoResponseDTO user) {
        this.user = user;
    }
}
