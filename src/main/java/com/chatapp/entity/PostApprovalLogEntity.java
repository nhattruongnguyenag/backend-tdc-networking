package com.chatapp.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "post_approval_logs")
public class PostApprovalLogEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private PostEntity post;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;


    public PostEntity getPost() {
        return this.post;
    }

    public void setPost(PostEntity post) {
        this.post = post;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}