package com.chatapp.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "messages")
public class MessageEntity extends BaseEntity {
    @Column(nullable = false, updatable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false, updatable = false)
    private String type;

    @Column(name = "status", nullable = false)
    private Byte status;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private UserEntity sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private UserEntity receiver;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "conversation_message", joinColumns = @JoinColumn(name = "message_id", nullable = false), inverseJoinColumns = @JoinColumn(name = "conversation_id", nullable = false))
    private List<ConversationEntity> conversations = new ArrayList<>();

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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public UserEntity getSender() {
        return sender;
    }

    public void setSender(UserEntity sender) {
        this.sender = sender;
    }

    public UserEntity getReceiver() {
        return receiver;
    }

    public void setReceiver(UserEntity receiver) {
        this.receiver = receiver;
    }

    public List<ConversationEntity> getConversations() {
        return conversations;
    }

    public void setConversations(List<ConversationEntity> conversations) {
        this.conversations = conversations;
    }
}
