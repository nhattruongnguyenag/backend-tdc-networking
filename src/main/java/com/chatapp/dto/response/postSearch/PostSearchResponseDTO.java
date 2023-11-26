package com.chatapp.dto.response.postSearch;

import java.util.List;

import com.chatapp.dto.BaseDTO;


import com.chatapp.dto.response.*;
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
}
