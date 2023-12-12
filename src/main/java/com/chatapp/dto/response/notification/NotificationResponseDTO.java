package com.chatapp.dto.response.notification;

import com.chatapp.dto.BaseDTO;
import com.chatapp.dto.response.user.UserInfoResponseDTO;

public class NotificationResponseDTO extends BaseDTO{
    private String content;
    private String status;
    private String type;
    private Object dataValue;
    private UserInfoResponseDTO userInteracted;

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

    public Object getDataValue() {
        return this.dataValue;
    }

    public void setDataValue(Object dataValue) {
        this.dataValue = dataValue;
    }

    public UserInfoResponseDTO getUserInteracted() {
        return this.userInteracted;
    }

    public void setUserInteracted(UserInfoResponseDTO userInteracted) {
        this.userInteracted = userInteracted;
    }
}
