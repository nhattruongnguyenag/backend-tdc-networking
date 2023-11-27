package com.chatapp.dto.request.post;

import lombok.Data;

@Data
public class PostFindRequestDTO {
    private Long userLogin;
    private String type;
    private String name;
}
