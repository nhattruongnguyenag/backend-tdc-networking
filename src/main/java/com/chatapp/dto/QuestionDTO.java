package com.chatapp.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuestionDTO extends BaseDTO {
    private String type;
    private String title;
    private Byte required;
    private List<ChoiceDTO> choices;
}
