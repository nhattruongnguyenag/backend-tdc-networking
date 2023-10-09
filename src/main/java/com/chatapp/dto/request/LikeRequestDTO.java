package com.chatapp.dto.request;

import com.chatapp.dto.BaseDTO;

import lombok.Data;

@Data
public class LikeRequestDTO extends BaseDTO{
    private Long id;
    private Long userId;
}
