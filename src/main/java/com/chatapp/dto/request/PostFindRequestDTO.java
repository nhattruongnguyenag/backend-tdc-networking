package com.chatapp.dto.request;

import lombok.Data;

@Data
public class PostFindRequestDTO {
    private Long userLogin;
    private String type;
    private String name;
}
