package com.chatapp.dto.response;

import lombok.Data;

@Data
public class SurveyResultResponseDTO{
    private Long questionId;
    private String type;
    private String title;
}
