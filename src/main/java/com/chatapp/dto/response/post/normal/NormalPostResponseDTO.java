package com.chatapp.dto.response.post.normal;


import com.chatapp.dto.response.post.PostInfoResponseDTO;

import lombok.Data;

@Data
public class NormalPostResponseDTO extends PostInfoResponseDTO {
    private String content;
}
