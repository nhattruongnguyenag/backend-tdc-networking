package com.chatapp.dto.response;

import lombok.Data;

@Data
public class PostRejectLogDTO {
    private Long id;
    private String content;
    private String createdAt;
}
