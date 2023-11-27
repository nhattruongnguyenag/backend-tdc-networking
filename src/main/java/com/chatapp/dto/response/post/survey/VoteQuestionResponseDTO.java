package com.chatapp.dto.response.post.survey;

import lombok.Data;

@Data
public class VoteQuestionResponseDTO {
    private Long voteQuestionId;
    private String content;
}
