package com.chatapp.dto.response.post;

import com.chatapp.dto.BaseDTO;
import com.chatapp.dto.response.group.GroupResponseDTO;
import com.chatapp.dto.response.image.ImageResponseDTO;
import com.chatapp.dto.response.post.comment.CommentResponeseDTO;
import com.chatapp.dto.response.user.UserInfoResponseDTO;
import com.chatapp.dto.response.user.like.UserLikeResponeDTO;

import lombok.Data;

import java.util.List;

@Data
public class PostInfoResponseDTO extends BaseDTO {
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
