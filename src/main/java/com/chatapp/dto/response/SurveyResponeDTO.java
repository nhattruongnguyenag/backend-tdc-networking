package com.chatapp.dto.response;

import java.util.List;

import lombok.Data;

@Data
public class SurveyResponeDTO extends PostInfoResponseDTO{
    private String title;
    private String description;
    private List<QuestionResponseDTO> questions;
    private Long isConduct;
}
