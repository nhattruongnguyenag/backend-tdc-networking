package com.chatapp.dto.request;

import java.util.List;

import lombok.Data;

@Data
public class SurveyUpdateRequestDTO {
    private Long id;
    private String title;
    private String description;
    private List<QuestionRequestDTO> questions;
}
