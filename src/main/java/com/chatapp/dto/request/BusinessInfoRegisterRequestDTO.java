package com.chatapp.dto.request;

import lombok.Data;

@Data
public class BusinessInfoRegisterRequestDTO {
    private String email;
    private String password;
    private String name;
    private String image;
    private String code;
    private String representor;
    private String taxCode;
    private String address;
    private String activeTime;
}
