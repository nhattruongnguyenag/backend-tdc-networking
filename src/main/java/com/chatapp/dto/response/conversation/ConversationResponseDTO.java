package com.chatapp.dto.response.conversation;

import com.chatapp.dto.BaseDTO;
import com.chatapp.dto.response.user.UserInfoResponseDTO;

import java.util.Date;

public class ConversationResponseDTO extends BaseDTO {
    private UserInfoResponseDTO sender;
    private UserInfoResponseDTO receiver;
    private Long countNewMessage;
    private String lastMessageContent;
    private Date lastMessageSentAt;
    private String lastMessageType;

    public UserInfoResponseDTO getSender() {
        return sender;
    }

    public void setSender(UserInfoResponseDTO sender) {
        this.sender = sender;
    }

    public UserInfoResponseDTO getReceiver() {
        return receiver;
    }

    public void setReceiver(UserInfoResponseDTO receiver) {
        this.receiver = receiver;
    }

    public Long getCountNewMessage() {
        return countNewMessage;
    }

    public void setCountNewMessage(Long countNewMessage) {
        this.countNewMessage = countNewMessage;
    }

    public String getLastMessageContent() {
        return lastMessageContent;
    }

    public void setLastMessageContent(String lastMessageContent) {
        this.lastMessageContent = lastMessageContent;
    }

    public Date getLastMessageSentAt() {
        return lastMessageSentAt;
    }

    public void setLastMessageSentAt(Date lastMessageSentAt) {
        this.lastMessageSentAt = lastMessageSentAt;
    }

    public String getLastMessageType() {
        return lastMessageType;
    }

    public void setLastMessageType(String lastMessageType) {
        this.lastMessageType = lastMessageType;
    }
}