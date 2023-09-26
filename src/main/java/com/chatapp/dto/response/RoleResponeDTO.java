package com.chatapp.dto.response;

import java.util.List;

import com.chatapp.dto.BaseDTO;


import lombok.*;

@Data
public class RoleResponeDTO extends BaseDTO{
    private String name;
    private String code;
    private List<UserInfoResponseDTO> users;
}