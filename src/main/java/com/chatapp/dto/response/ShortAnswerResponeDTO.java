package com.chatapp.dto.response;

import lombok.Data;

@Data
public class ShortAnswerResponeDTO {
    UserInfoResponseDTO user;
    QuestionResponeDTO question;
    String content;
}
