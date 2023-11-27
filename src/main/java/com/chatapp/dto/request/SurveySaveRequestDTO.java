package com.chatapp.dto.request;

import java.util.List;

import com.chatapp.dto.BaseDTO;
import lombok.Data;

@Data
public class SurveySaveRequestDTO extends BaseDTO {
    private String type;
    private List<String> images;
    private Long userId;
    private Long groupId;
    private String title;
    private String description;
    private List<QuestionRequestDTO> questions;
}
