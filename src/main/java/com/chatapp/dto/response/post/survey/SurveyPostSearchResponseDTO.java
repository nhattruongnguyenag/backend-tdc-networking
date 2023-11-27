package com.chatapp.dto.response.post.survey;

import lombok.Data;

import java.util.List;

import com.chatapp.dto.response.post.PostSearchResponseDTO;

@Data
public class SurveyPostSearchResponseDTO extends PostSearchResponseDTO {
    private String title;
    private String description;
    private List<QuestionResponseDTO> questions;
    private Long isConduct;
}
