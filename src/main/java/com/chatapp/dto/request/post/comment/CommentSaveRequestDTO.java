package com.chatapp.dto.request.post.comment;

import lombok.Data;

@Data
public class CommentSaveRequestDTO {
    private Long postId;
    private Long userId;
    private Long parentCommentId;
    private String content;
}