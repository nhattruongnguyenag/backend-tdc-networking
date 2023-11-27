package com.chatapp.dto.request.post.survey;

import java.util.List;

import lombok.Data;

@Data
public class SurveyUpdateRequestDTO {
    private Long id;
    private String title;
    private String description;
    private List<QuestionRequestDTO> questions;
}
