package com.chatapp.dto.response.post.survey;

import java.util.List;

import com.chatapp.dto.response.post.PostInfoResponseDTO;

import lombok.Data;

@Data
public class SurveyResponeDTO extends PostInfoResponseDTO {
    private String title;
    private String description;
    private List<QuestionResponseDTO> questions;
    private Long isConduct;
}
