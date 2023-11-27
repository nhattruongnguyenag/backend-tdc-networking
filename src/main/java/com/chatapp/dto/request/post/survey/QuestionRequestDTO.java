package com.chatapp.dto.request.post.survey;

import java.util.List;

import com.chatapp.dto.response.post.survey.ChoiceDTO;

import lombok.Data;

@Data
public class QuestionRequestDTO {
    private String type;
    private String title;
    private Long required;
    private List<ChoiceDTO> choices;
}
