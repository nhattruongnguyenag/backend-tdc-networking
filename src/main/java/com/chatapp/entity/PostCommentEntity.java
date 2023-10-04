package com.chatapp.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "posts_comments")
public class PostCommentEntity extends BaseEntity {

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private PostEntity post;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "postComment", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PostCommentEntity> postComments = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "parent_comment_id", nullable = true)
    private PostCommentEntity postComment;
}