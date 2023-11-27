package com.chatapp.dto.request.post.comment;

import lombok.Data;

@Data
public class CommentDeleteRequestDTO {
    private Long commentId;
    private Long postId;
    private Long userId;
}
