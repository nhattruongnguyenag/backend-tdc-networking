package com.chatapp.dto.response;

import com.chatapp.dto.BaseDTO;


import lombok.*;

@Data
public class PostInfoResponeDTO extends BaseDTO{
    private Byte status;
    private Byte active;
    private UserInfoResponseDTO user;
}
