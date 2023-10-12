package com.chatapp.dto.request;

import java.util.List;

import lombok.Data;

@Data
public class SurveySaveRequestDTO {
    private String type;
    private List<String> images;
    private Long userId;
    private String title;
    private String description;
    private List<QuestionRequestDTO> questions;
}
