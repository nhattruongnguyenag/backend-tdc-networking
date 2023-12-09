package com.chatapp.dto.response.post.comment;


import java.util.List;

import com.chatapp.dto.BaseDTO;
import com.chatapp.dto.response.user.UserCommentResponeDTO;

public class CommentResponeseDTO extends BaseDTO{
    private String content;
    private UserCommentResponeDTO user;
    private Long postId;
    private ParentCommentResponseDTO parent;
    private List<CommentResponeseDTO> childrens;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserCommentResponeDTO getUser() {
        return user;
    }

    public void setUser(UserCommentResponeDTO user) {
        this.user = user;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public ParentCommentResponseDTO getParent() {
        return parent;
    }

    public void setParent(ParentCommentResponseDTO parent) {
        this.parent = parent;
    }

    public List<CommentResponeseDTO> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<CommentResponeseDTO> childrens) {
        this.childrens = childrens;
    }
}
