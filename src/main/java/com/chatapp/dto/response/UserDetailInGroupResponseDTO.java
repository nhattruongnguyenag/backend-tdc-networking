package com.chatapp.dto.response;

import java.util.List;

import com.chatapp.dto.BaseDTO;

import lombok.Data;

@Data
public class UserDetailInGroupResponseDTO{
    UserInfoResponseDTO user;
    List<BaseDTO> posts;
}
