package com.chatapp.dto;

import lombok.Data;

import java.util.List;

@Data
public class SurveyDTO extends BaseDTO {
    private String title;
    private String description;
    private List<QuestionDTO> questions;
}
