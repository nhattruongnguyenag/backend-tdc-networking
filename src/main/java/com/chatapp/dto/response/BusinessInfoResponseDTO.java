package com.chatapp.dto.response;


import lombok.*;

@Data
public class BusinessInfoResponseDTO extends UserInfoResponseDTO{
    String representor;
    String taxCode;
    String address;
    String activeTime;
}
