package com.chatapp.dto.request;

import lombok.Data;

@Data
public class EmailRequestDTO {
    private String to;
    private String subject;
    private String content;
}
