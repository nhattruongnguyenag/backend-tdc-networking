package com.chatapp.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "vote_answers")
public class VoteAnswerEntity extends BaseEntity {
    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "count_vote", nullable = false)
    private Integer countVote = 0;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private QuestionEntity question;

    @ManyToMany(mappedBy = "voteAnswers", fetch = FetchType.LAZY)
    private List<UserEntity> users = new ArrayList<>();

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

    public List<UserEntity> getUsers() {
        return this.users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

}