package com.chatapp.dto.request;

import java.util.List;

import lombok.Data;

@Data
public class QuestionRequestDTO {
    private String type;
    private String title;
    private List<String> choices;
}
