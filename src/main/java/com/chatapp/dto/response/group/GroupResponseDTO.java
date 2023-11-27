package com.chatapp.dto.response.group;

import com.chatapp.dto.BaseDTO;

import lombok.Data;

@Data
public class GroupResponseDTO extends BaseDTO{
    private Byte active;
    private String background;
    private String name;
    private String code;
}
