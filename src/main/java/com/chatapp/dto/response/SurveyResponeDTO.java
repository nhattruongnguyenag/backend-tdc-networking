package com.chatapp.dto.response;

import java.util.List;

import lombok.Data;

@Data
public class SurveyResponeDTO extends PostInfoResponseDTO{
    private List<QuestionResponseDTO> questions;
}
