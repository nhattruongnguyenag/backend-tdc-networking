package com.chatapp.dto.response;

import com.chatapp.dto.BaseDTO;

import lombok.Data;

@Data
public class QuestionResponseDTO extends BaseDTO{
    private PostInfoResponseDTO post;
    private String title;
    private String type;
}
