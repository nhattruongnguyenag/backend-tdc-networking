package com.chatapp.dto.response.user;

import lombok.Data;

@Data
public class UserFindResponseDTO {
    private Long id;
    private String name;
    private String image;
    private Boolean isFollow;
}
