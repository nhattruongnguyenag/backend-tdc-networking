package com.chatapp.dto.request.post.normal;

import java.util.List;

import lombok.Data;

@Data
public class NormalPostUpdateRequestDTO {
    private Long postId;
    private String content; 
    private List<String> images;
}
