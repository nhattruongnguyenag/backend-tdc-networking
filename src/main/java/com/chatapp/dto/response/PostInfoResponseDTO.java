package com.chatapp.dto.response;

import java.util.List;

import com.chatapp.dto.BaseDTO;


import lombok.*;

@Data
public class PostInfoResponseDTO extends BaseDTO{
    private Byte status;
    private Byte active;
    private String type;
    private UserInfoResponseDTO user;
    private List<String> likes;
    private List<String> images;
    private List<CommentResponeseDTO> comment;
}
