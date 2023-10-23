package com.chatapp.dto.request;

import java.util.List;

import lombok.Data;

@Data
public class SurveyAnswerRequestDTO {
    private Long user_id;
    private List<AnswerRequestDTO> answers;
}
