package com.chatapp.dto.request.post.survey;

import java.util.List;

import lombok.Data;

@Data
public class SurveyAnswerRequestDTO {
    private Long user_id;
    private Long post_id;
    private List<AnswerRequestDTO> answers;
}
