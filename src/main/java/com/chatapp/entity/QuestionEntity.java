package com.chatapp.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questions")
public class QuestionEntity extends BaseEntity {
    
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "required", nullable = false)
    private Byte required;
    
    @ManyToOne
    @JoinColumn(name = "survey_id", nullable = false)
    private SurveyPostEntity survey;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ShortAnswerEntity> shortAnswers = new ArrayList<>();

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<VoteAnswerEntity> voteAnswers = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public SurveyPostEntity getSurvey() {
        return survey;
    }

    public void setSurvey(SurveyPostEntity surveyPostEntity) {
        this.survey = surveyPostEntity;
    }

    public List<ShortAnswerEntity> getShortAnswers() {
        return shortAnswers;
    }

    public void setShortAnswers(List<ShortAnswerEntity> shortAnswers) {
        this.shortAnswers = shortAnswers;
    }

    public List<VoteAnswerEntity> getVoteAnswers() {
        return voteAnswers;
    }

    public void setVoteAnswers(List<VoteAnswerEntity> voteAnswers) {
        this.voteAnswers = voteAnswers;
    }

    public Byte getRequired() {
        return required;
    }

    public void setRequired(Byte required) {
        this.required = required;
    }
}