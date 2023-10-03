package com.chatapp.dto.response;

import com.chatapp.dto.BaseDTO;

import lombok.Data;

@Data
public class NormalPostResponeDTO extends BaseDTO{
    private PostInfoResponeDTO post;
    private String content;
}
