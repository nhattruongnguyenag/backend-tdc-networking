package com.chatapp.dto.request.post.survey;

import java.util.List;

import lombok.Data;

@Data
public class AnswerRequestDTO {
    private Long question_id;
    private String content;
    private List<Long> choices_ids;
}
