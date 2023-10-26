package com.chatapp.dto.response;

import java.util.List;

import com.chatapp.dto.BaseDTO;

import lombok.Data;

@Data
public class QuestionResponseDTO extends BaseDTO{
    private String title;
    private String type;
    private List<VoteQuestionResponseDTO> choices;
}
