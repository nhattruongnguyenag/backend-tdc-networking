package com.chatapp.dto.request;

import lombok.Data;

@Data
public class BusinessInfoUpdateOrSaveRequestDTO {
    private Long id;
    private String email;
    private String name;
    private String image;
    private String phone;
    private String code;
    private String representor;
    private String taxCode;
    private String address;
    private String activeTime;
}
