package com.chatapp.dto.request;

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
}
