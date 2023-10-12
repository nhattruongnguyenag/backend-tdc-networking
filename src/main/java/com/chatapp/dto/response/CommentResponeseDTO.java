package com.chatapp.dto.response;


import java.util.List;

import com.chatapp.dto.BaseDTO;

import lombok.Data;

@Data
public class CommentResponeseDTO extends BaseDTO{
    private String content;
    private UserCommentResponeDTO user;
    private Long postId;
    private Long parentId;
    private List<CommentResponeseDTO> childrens;
}
