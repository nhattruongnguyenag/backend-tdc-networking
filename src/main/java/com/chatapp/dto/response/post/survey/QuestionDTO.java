package com.chatapp.dto.response.post.survey;

import com.chatapp.dto.BaseDTO;

import java.util.ArrayList;
import java.util.List;

public class QuestionDTO extends BaseDTO {
    private String type;
    private String title;
    private Byte required;
    private List<ChoiceDTO> choices = new ArrayList<>();

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

    public Byte getRequired() {
        return required;
    }

    public void setRequired(Byte required) {
        this.required = required;
    }

    public List<ChoiceDTO> getChoices() {
        return choices;
    }

    public void setChoices(List<ChoiceDTO> choices) {
        this.choices = choices;
    }
}
