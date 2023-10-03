package com.chatapp.dto.response;

import com.chatapp.dto.BaseDTO;

import lombok.Data;

@Data
public class QuestionResponeDTO extends BaseDTO{
    private PostInfoResponeDTO post;
    private String title;
    private String type;
}
