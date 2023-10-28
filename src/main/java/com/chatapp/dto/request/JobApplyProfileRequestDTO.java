package com.chatapp.dto.request;

import lombok.Data;

@Data
public class JobApplyProfileRequestDTO {
    private Long post_id;
    private Long user_id;
    private String cv_url;
}
