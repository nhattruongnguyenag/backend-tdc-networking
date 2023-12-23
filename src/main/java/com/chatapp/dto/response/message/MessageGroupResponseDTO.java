package com.chatapp.dto.response.message;

import java.util.List;

public class MessageGroupResponseDTO {
    private Byte receiverTypingStatus;
    private List<MessageResponseDTO> messages;

    public Byte getReceiverTypingStatus() {
        return receiverTypingStatus;
    }

    public void setReceiverTypingStatus(Byte receiverTypingStatus) {
        this.receiverTypingStatus = receiverTypingStatus;
    }

    public List<MessageResponseDTO> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageResponseDTO> messages) {
        this.messages = messages;
    }
}
