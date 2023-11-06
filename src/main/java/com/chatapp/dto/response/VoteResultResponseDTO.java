package com.chatapp.dto.response;

import java.util.List;

import com.chatapp.dto.response.items.ChoiceItemResponseDTO;

import lombok.Data;

@Data
public class VoteResultResponseDTO extends SurveyResultResponseDTO{
    private List<ChoiceItemResponseDTO> choices;
}
