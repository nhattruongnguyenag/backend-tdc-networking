package com.chatapp.dto.response.message;

import lombok.Data;

import java.util.Date;

import com.chatapp.dto.BaseDTO;
import com.chatapp.dto.response.user.UserMessageResponseDTO;

@Data
public class MessageResponseDTO extends BaseDTO{
    private String content;
    private String type;
    private UserMessageResponseDTO sender;
    private UserMessageResponseDTO receiver;
    private Long status;
    private Date createdAt;
}
