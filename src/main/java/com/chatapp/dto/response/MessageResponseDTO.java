package com.chatapp.dto.response;

import lombok.Data;

import java.util.Date;

import com.chatapp.dto.BaseDTO;

@Data
public class MessageResponseDTO extends BaseDTO{
    private String content;
    private String type;
    private UserMessageResponseDTO sender;
    private UserMessageResponseDTO receiver;
    private Long status;
    private Date createdAt;
}
