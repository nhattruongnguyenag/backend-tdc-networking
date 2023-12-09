package com.chatapp.dto.response.post.normal;

import com.chatapp.dto.response.post.PostSearchResponseDTO;

public class NormalPostSearchResponseDTO extends PostSearchResponseDTO {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
