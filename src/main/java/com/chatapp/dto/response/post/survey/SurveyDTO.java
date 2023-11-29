package com.chatapp.dto.response.post.survey;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SurveyDTO {
    private Long postId;
    private String title;
    private String description;
    private List<QuestionDTO> questions = new ArrayList<>();
}
