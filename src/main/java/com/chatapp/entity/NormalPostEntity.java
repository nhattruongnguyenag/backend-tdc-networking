package com.chatapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "normal_posts")
public class NormalPostEntity extends BaseEntity {

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @OneToOne
    @JoinColumn(name = "post_id", nullable = false)
    private PostEntity post;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public PostEntity getPost() {
        return post;
    }

    public void setPost(PostEntity post) {
        this.post = post;
    }
}