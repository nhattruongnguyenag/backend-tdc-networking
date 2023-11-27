package com.chatapp.dto.request.post.log;

import lombok.Data;

@Data
public class PostLogRequestDTO {
    private String content;
    private Long postId;
}
