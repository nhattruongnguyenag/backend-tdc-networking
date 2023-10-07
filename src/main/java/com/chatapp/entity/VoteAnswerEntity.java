package com.chatapp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "vote_answers")
public class VoteAnswerEntity extends BaseEntity {
    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "count_vote", nullable = false)
    private Integer countVote;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private QuestionEntity question;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCountVote() {
        return countVote;
    }

    public void setCountVote(Integer countVote) {
        this.countVote = countVote;
    }

    public QuestionEntity getQuestion() {
        return question;
    }

    public void setQuestion(QuestionEntity question) {
        this.question = question;
    }
}