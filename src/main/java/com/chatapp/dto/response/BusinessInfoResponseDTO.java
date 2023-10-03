package com.chatapp.dto.response;

import com.chatapp.dto.BaseDTO;

import lombok.*;

@Data
public class BusinessInfoResponseDTO extends BaseDTO{
    UserInfoResponseDTO user;
    String representor;
    String taxCode;
    String address;
    String activeTime;
}
