package com.chatapp.dto.response.post.normal;

import com.chatapp.dto.response.post.PostSearchResponseDTO;

import lombok.Data;

@Data
public class NormalPostSearchResponseDTO extends PostSearchResponseDTO {
    private String content;
}
