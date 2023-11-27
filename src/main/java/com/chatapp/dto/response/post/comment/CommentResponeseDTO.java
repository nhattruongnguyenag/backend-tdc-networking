package com.chatapp.dto.response.post.comment;


import java.util.List;

import com.chatapp.dto.BaseDTO;
import com.chatapp.dto.response.user.UserCommentResponeDTO;

import lombok.Data;

@Data
public class CommentResponeseDTO extends BaseDTO{
    private String content;
    private UserCommentResponeDTO user;
    private Long postId;
    private ParentCommentResponseDTO parent;
    private List<CommentResponeseDTO> childrens;
}
