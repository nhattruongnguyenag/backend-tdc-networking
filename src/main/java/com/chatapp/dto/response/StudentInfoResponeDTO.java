package com.chatapp.dto.response;

import lombok.*;

@Data
public class StudentInfoResponeDTO extends UserInfoResponseDTO {
    String facultyName;
    String major;
    String studentCode;
}
