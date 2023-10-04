package com.chatapp.dto.request;

import java.util.List;

import lombok.Data;

@Data
public class SurveySaveRequestDTO {
    private String type;
    private Long userId;
    private List<QuestionRequestDTO> questions;
}
