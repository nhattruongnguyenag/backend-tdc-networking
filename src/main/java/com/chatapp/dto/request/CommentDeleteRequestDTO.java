package com.chatapp.dto.request;

import lombok.Data;

@Data
public class CommentDeleteRequestDTO {
    private Long commentId;
    private Long postId;
    private Long userId;
}
