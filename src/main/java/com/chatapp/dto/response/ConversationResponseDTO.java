package com.chatapp.dto.response;

import com.chatapp.dto.BaseDTO;
import lombok.Data;

import java.util.Date;

@Data
public class ConversationResponseDTO extends BaseDTO {
    private UserInfoResponseDTO sender;
    private UserInfoResponseDTO receiver;
    private Long countNewMessage;
    private String lastMessageContent;
    private Date lastMessageSentAt;
    private String lastMessageType;
}