package com.chatapp.dto.response;

import lombok.*;

@Data
public class BusinessInfoResponseDTO {
    UserInfoResponseDTO user;
    String representor;
    String taxCode;
    String address;
    String activeTime;
}
