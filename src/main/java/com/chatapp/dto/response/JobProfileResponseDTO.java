package com.chatapp.dto.response;

import com.chatapp.dto.BaseDTO;

import lombok.Data;

@Data
public class JobProfileResponseDTO extends BaseDTO{
    private PostInfoResponseDTO post;
    private UserInfoResponseDTO user;
    private String cvUrl;
}
