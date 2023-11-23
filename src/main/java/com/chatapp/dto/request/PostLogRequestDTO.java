package com.chatapp.dto.request;

import lombok.Data;

@Data
public class PostLogRequestDTO {
    private String content;
    private Long postId;
}
