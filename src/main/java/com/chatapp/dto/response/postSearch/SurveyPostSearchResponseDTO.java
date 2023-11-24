package com.chatapp.dto.response.postSearch;

import com.chatapp.dto.response.QuestionResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class SurveyPostSearchResponseDTO extends PostSearchResponseDTO {
    private String title;
    private String description;
    private List<QuestionResponseDTO> questions;
}
