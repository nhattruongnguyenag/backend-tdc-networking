package com.chatapp.dto.request;

import lombok.Data;

@Data
public class NormalPostUpdateOrSaveRequestDTO {
    private Long id;
    private Long userId;
    private String type;
    private String content;
}
