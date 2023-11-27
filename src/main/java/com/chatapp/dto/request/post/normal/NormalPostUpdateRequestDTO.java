package com.chatapp.dto.request.post.normal;

import lombok.Data;

@Data
public class NormalPostUpdateRequestDTO {
    private Long postId;
    private String content; 
}
