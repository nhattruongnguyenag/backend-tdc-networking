package com.chatapp.dto.response.post.survey;

import lombok.Data;

import java.util.List;

import com.chatapp.dto.BaseDTO;

@Data
public class QuestionDTO extends BaseDTO {
    private String type;
    private String title;
    private Byte required;
    private List<ChoiceDTO> choices;
}
