package com.chatapp.dto.response.user.business;


import com.chatapp.dto.response.user.UserInfoResponseDTO;

import lombok.*;

@Data
public class BusinessInfoResponseDTO extends UserInfoResponseDTO{
    String representor;
    String taxCode;
    String address;
    String activeTime;
}
