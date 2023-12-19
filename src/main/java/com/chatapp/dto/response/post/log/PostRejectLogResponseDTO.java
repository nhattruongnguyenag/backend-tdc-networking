package com.chatapp.dto.response.post.log;

import com.chatapp.dto.response.post.PostSearchResponseDTO;

public class PostRejectLogResponseDTO {
    private Long id;
    private String content;
    private String createdAt;
    private PostSearchResponseDTO post;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public PostSearchResponseDTO getPost() {
        return this.post;
    }

    public void setPost(PostSearchResponseDTO post) {
        this.post = post;
    }

}
