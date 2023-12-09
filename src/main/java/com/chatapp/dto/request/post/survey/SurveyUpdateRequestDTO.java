package com.chatapp.dto.request.post.survey;

import java.util.List;


public class SurveyUpdateRequestDTO {
    private Long id;
    private String title;
    private String description;
    private List<QuestionRequestDTO> questions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<QuestionRequestDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionRequestDTO> questions) {
        this.questions = questions;
    }
}
