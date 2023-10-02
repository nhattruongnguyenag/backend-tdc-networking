package com.chatapp.dto.response;

import lombok.Data;

@Data
public class QuestionResponeDTO {
    private PostInfoResponeDTO post;
    private String title;
    private String type;
}
