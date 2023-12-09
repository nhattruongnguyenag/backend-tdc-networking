package com.chatapp.dto.response.message;

import com.chatapp.dto.BaseDTO;
import com.chatapp.dto.response.user.UserMessageResponseDTO;

import java.util.Date;

public class MessageResponseDTO extends BaseDTO{
    private String content;
    private String type;
    private UserMessageResponseDTO sender;
    private UserMessageResponseDTO receiver;
    private Long status;
    private Date createdAt;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UserMessageResponseDTO getSender() {
        return sender;
    }

    public void setSender(UserMessageResponseDTO sender) {
        this.sender = sender;
    }

    public UserMessageResponseDTO getReceiver() {
        return receiver;
    }

    public void setReceiver(UserMessageResponseDTO receiver) {
        this.receiver = receiver;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
