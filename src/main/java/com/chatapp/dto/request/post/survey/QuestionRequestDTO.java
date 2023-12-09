package com.chatapp.dto.request.post.survey;

import com.chatapp.dto.response.post.survey.ChoiceDTO;
import com.chatapp.dto.response.post.survey.TextPreviewResponseDTO;

import java.util.List;

public class QuestionRequestDTO {
    private String type;
    private String title;
    private Long required;
    private List<ChoiceDTO> choices;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getRequired() {
        return required;
    }

    public void setRequired(Long required) {
        this.required = required;
    }

    public List<ChoiceDTO> getChoices() {
        return choices;
    }

    public void setChoices(List<ChoiceDTO> choices) {
        this.choices = choices;
    }
}
