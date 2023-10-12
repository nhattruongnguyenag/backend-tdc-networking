package com.chatapp.dto.response;

import java.util.List;

import lombok.Data;

@Data
public class CommentResponeseDTO {
    private String content;
    private String user;
    private Long postId;
    private List<CommentResponeseDTO> childrens;
}
