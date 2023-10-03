package com.chatapp.dto.response;


import com.chatapp.dto.BaseDTO;

import lombok.Data;

@Data
public class UserInfoResponseDTO extends BaseDTO {
    private String email;
    private String name;
    private String image;
    private Byte status;
    private String roleCodes;
}
