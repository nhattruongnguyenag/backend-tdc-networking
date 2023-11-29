package com.chatapp.dto.response.post;

import java.util.List;

import com.chatapp.dto.BaseDTO;


import com.chatapp.dto.response.*;
import com.chatapp.dto.response.group.GroupResponseDTO;
import com.chatapp.dto.response.image.ImageResponseDTO;
import com.chatapp.dto.response.post.comment.CommentResponeseDTO;
import com.chatapp.dto.response.user.UserInfoResponseDTO;
import com.chatapp.dto.response.user.like.UserLikeResponeDTO;

import lombok.*;

@Data
public class PostSearchResponseDTO {
    private Long id;
    private String createdAt;
    private String updatedAt;
    private Byte status;
    private Byte active;
    private String type;
    private UserInfoResponseDTO user;
    private GroupResponseDTO group;
    private List<UserLikeResponeDTO> likes;
    private List<ImageResponseDTO> images;
    private List<CommentResponeseDTO> comment;
    private Long commentQuantity;
    private Long isSave;
}
