package com.chatapp.dto.request;

import lombok.Data;

@Data
public class NormalPostUpdateRequestDTO {
    private Long postId;
    private String content; 
}
