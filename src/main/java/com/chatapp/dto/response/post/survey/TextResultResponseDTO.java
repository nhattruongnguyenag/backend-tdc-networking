package com.chatapp.dto.response.post.survey;

import java.util.List;

public class TextResultResponseDTO extends SurveyResultResponseDTO{
    private List<String> answers;

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
}
