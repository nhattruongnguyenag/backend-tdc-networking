package com.chatapp.dto.response.post.normal;


import com.chatapp.dto.response.post.PostInfoResponseDTO;

public class NormalPostResponseDTO extends PostInfoResponseDTO {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
