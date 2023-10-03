package com.chatapp.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "questions")
public class QuestionEntity extends BaseEntity {
    
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "type", nullable = false)
    private String type;
    
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private PostEntity post;

    @OneToOne(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private ShortAnswerEntity shortAnswer;

    @OneToOne(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private VoteAnswerEntity voteAnswer;
}