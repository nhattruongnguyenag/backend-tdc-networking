package com.chatapp.dto.response;

import java.util.List;

import lombok.Data;

@Data
public class VotePreviewResponseDTO extends SurveyPreviewResponseDTO{
    private List<Long> choices_ids;
}
