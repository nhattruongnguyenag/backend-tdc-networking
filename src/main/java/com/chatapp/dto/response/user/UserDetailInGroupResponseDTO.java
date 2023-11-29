package com.chatapp.dto.response.user;

import java.util.List;

import com.chatapp.dto.response.post.PostSearchResponseDTO;

import lombok.Data;

@Data
public class UserDetailInGroupResponseDTO{
    UserInfoResponseDTO user;
    List<PostSearchResponseDTO> posts;
    Boolean isFollow;
}
