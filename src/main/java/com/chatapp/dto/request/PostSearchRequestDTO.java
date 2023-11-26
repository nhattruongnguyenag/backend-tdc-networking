package com.chatapp.dto.request;

import lombok.Data;

@Data
public class PostSearchRequestDTO {
    private String group;
    private String ownerFaculty;
    private String status;
    private String active;
    private String userId;
}
