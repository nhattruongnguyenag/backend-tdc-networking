package com.chatapp.dto.request;

import lombok.Data;

@Data
public class ShortAnswerSaveRequestDTO {
    private Long userId;
    private String type;
    private String title;
    private String questionType;
    private String content;
}
