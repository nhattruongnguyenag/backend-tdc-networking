package com.chatapp.dto.request.user.business;

import lombok.Data;

@Data
public class BusinessInfoRegisterRequestDTO {
    private String email;
    private String password;
    private String name;
    private String image;
    private String phone;
    private String code;
    private String representor;
    private String taxCode;
    private String address;
    private String activeTime;
    private String subject;
    private String content;
}
