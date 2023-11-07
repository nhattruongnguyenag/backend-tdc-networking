package com.chatapp.dto.response;

import java.util.List;

import lombok.Data;

@Data
public class TextResultResponseDTO extends SurveyResultResponseDTO{
    private List<String> answers;
}
