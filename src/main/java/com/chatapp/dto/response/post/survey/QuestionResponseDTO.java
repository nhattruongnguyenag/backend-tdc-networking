package com.chatapp.dto.response.post.survey;

import com.chatapp.dto.BaseDTO;

import java.util.List;

public class QuestionResponseDTO extends BaseDTO {
    private String title;
    private String type;
    private Long required;
    private List<VoteQuestionResponseDTO> choices;

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

    public Long getRequired() {
        return required;
    }

    public void setRequired(Long required) {
        this.required = required;
    }

    public List<VoteQuestionResponseDTO> getChoices() {
        return choices;
    }

    public void setChoices(List<VoteQuestionResponseDTO> choices) {
        this.choices = choices;
    }
}
