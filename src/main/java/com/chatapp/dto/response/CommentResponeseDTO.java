package com.chatapp.dto.response;


import com.chatapp.dto.BaseDTO;

import lombok.Data;

@Data
public class CommentResponeseDTO extends BaseDTO{
    private String content;
    private String user;
    private Long postId;
    private CommentResponeseDTO parent;
}
