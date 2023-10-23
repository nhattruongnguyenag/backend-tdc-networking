package com.chatapp.dto.request;


import java.util.List;

import lombok.Data;

@Data
public class NormalPostUpdateOrSaveRequestDTO {
    private Long id;
    private Long userId;
    private Long groupId;
    private String type;
    private List<String> images;
    private String content;
}
