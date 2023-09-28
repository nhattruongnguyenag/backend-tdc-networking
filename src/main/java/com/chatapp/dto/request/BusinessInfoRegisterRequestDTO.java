package com.chatapp.dto.request;

import lombok.Data;

@Data
public class BusinessInfoRegisterRequestDTO {
    private String email;
    private String password;
    private String fullname;
    private String image;
    private String representor;
    private String taxCode;
    private String address;
    private String activeTime;
}
