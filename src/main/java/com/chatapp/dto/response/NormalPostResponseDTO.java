package com.chatapp.dto.response;

import com.chatapp.dto.BaseDTO;

import lombok.Data;

@Data
public class NormalPostResponseDTO extends BaseDTO{
    private PostInfoResponseDTO post;
    private String content;
}
