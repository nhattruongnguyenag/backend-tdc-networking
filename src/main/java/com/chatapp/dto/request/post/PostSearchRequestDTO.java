package com.chatapp.dto.request.post;

import lombok.Data;

@Data
public class PostSearchRequestDTO {
    private String group;
    private String ownerFaculty;
    private Integer status;
    private Integer active;
    private Integer userId;
    private Long postId;
    private String type;
    private Long userLogin;
    private String search;
}
