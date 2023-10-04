package com.chatapp.dto.response;

import com.chatapp.dto.BaseDTO;
import lombok.Data;

@Data
public class ConversationResponseDTO extends BaseDTO {
    private Long senderId;
    private Long receiverId;
}