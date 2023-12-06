package com.chatapp.dto.request.option;

import lombok.Data;

@Data
public class OptionRequestDTO {
    private Long userId;
    private String optionKey;
    private String value;
}
