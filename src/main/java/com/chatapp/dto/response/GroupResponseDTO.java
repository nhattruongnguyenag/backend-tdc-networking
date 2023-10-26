package com.chatapp.dto.response;

import com.chatapp.dto.BaseDTO;

import lombok.Data;

@Data
public class GroupResponseDTO extends BaseDTO{
    private Byte active;
    private String image;
    private String name;
    private String code;
}
