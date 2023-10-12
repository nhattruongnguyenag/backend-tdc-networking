package com.chatapp.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class MessageGroupResponseDTO {
    private Byte receiverTypingStatus;
    private List<MessageResponseDTO> messages;
}
