package com.chatapp.dto.response;


import java.util.List;

import com.chatapp.dto.BaseDTO;

import lombok.Data;

@Data
public class UserInfoResponseDTO extends BaseDTO {
    private String email;
    private String name;
    private String image;
    private String phone;
    private Byte status;
    private Byte active;
    private String code;
    private Byte isTyping;
    private Byte isMessageConnect;
    private String roleCodes;
    private List<UserFollowResponseDTO> follows;
    private String lastActive;
}
