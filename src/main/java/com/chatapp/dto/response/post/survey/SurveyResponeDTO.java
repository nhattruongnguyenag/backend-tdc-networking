package com.chatapp.dto.response.post.survey;

import com.chatapp.dto.response.post.PostInfoResponseDTO;

import java.util.List;

public class SurveyResponeDTO extends PostInfoResponseDTO {
    private String title;
    private String description;
    private List<QuestionResponseDTO> questions;
    private Long isConduct;

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

    public List<QuestionResponseDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionResponseDTO> questions) {
        this.questions = questions;
    }

    public Long getIsConduct() {
        return isConduct;
    }

    public void setIsConduct(Long isConduct) {
        this.isConduct = isConduct;
    }
}
