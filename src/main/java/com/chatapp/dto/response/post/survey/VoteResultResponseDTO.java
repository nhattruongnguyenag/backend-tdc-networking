package com.chatapp.dto.response.post.survey;

import com.chatapp.dto.response.items.ChoiceItemResponseDTO;

import java.util.List;

public class VoteResultResponseDTO extends SurveyResultResponseDTO{
    private List<ChoiceItemResponseDTO> choices;

    public List<ChoiceItemResponseDTO> getChoices() {
        return choices;
    }

    public void setChoices(List<ChoiceItemResponseDTO> choices) {
        this.choices = choices;
    }
}
