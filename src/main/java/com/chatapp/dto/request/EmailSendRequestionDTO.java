package com.chatapp.dto.request;

import lombok.Data;

@Data
public class EmailSendRequestionDTO {
    private String to;
    private String subject;
    private String content;
}
