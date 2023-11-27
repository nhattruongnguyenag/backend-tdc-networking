package com.chatapp.dto.response.message;

import lombok.Data;

import java.util.List;

@Data
public class MessageGroupResponseDTO {
    private Byte receiverTypingStatus;
    private List<MessageResponseDTO> messages;
}
