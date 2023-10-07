package com.chatapp.entity;

import jakarta.persistence.*;

import java.util.List;

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
    private List<PostCommentEntity> postComments;

    @ManyToOne
    @JoinColumn(name = "parent_comment_id", nullable = true)
    private PostCommentEntity postComment;

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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<PostCommentEntity> getPostComments() {
        return postComments;
    }

    public void setPostComments(List<PostCommentEntity> postComments) {
        this.postComments = postComments;
    }

    public PostCommentEntity getPostComment() {
        return postComment;
    }

    public void setPostComment(PostCommentEntity postComment) {
        this.postComment = postComment;
    }
}