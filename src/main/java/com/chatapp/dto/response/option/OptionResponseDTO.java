package com.chatapp.dto.response.option;

import lombok.Data;

@Data
public class OptionResponseDTO {
    private Long userId;
    private String optionKey;
    private String value;
}
