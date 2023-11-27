package com.chatapp.dto.response.post.survey;

import lombok.Data;

@Data
public class SurveyResultResponseDTO{
    private Long questionId;
    private String type;
    private String title;
}
