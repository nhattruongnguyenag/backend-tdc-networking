package com.chatapp.dto.request.user.business;

import lombok.Data;

@Data
public class BusinessInfoUpdateOrSaveRequestDTO {
    private Long id;
    private String email;
    private String name;
    private String image;
    private String background;
    private String phone;
    private String representor;
    private String taxCode;
    private String address;
    private String activeTime;
}
