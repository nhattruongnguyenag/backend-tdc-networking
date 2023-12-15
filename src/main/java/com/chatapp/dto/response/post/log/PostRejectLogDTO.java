package com.chatapp.dto.response.post.log;

import com.chatapp.dto.response.group.GroupResponseDTO;

public class PostRejectLogDTO {
    private Long id;
    private String content;
    private String createdAt;
    private GroupResponseDTO group;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public GroupResponseDTO getGroup() {
        return this.group;
    }

    public void setGroup(GroupResponseDTO group) {
        this.group = group;
    }
}
