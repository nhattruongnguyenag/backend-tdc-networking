package com.chatapp.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "survey_posts")
public class SurveyPostEntity extends BaseEntity {

    @Column(name = "title", nullable = false)
    private String title;

    @OneToOne
    @JoinColumn(name = "post_id", nullable = false)
    private PostEntity post;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<QuestionEntity> questions = new ArrayList<>();

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PostEntity getPost() {
        return this.post;
    }

    public void setPost(PostEntity post) {
        this.post = post;
    }

    public List<QuestionEntity> getQuestions() {
        return this.questions;
    }

    public void setQuestions(List<QuestionEntity> questions) {
        this.questions = questions;
    }
}
