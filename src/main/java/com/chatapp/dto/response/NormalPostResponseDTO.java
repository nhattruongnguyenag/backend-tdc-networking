package com.chatapp.dto.response;


import lombok.Data;

@Data
public class NormalPostResponseDTO extends PostInfoResponseDTO {
    private String content;
}
