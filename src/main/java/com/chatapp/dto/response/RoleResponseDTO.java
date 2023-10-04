package com.chatapp.dto.response;


import com.chatapp.dto.BaseDTO;


import lombok.*;

@Data
public class RoleResponseDTO extends BaseDTO{
    private String name;
    private String code;
}