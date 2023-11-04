package com.chatapp.dto.response;

import lombok.*;

@Data
public class StudentInfoResponseDTO extends UserInfoResponseDTO {
    String facultyName;
    String major;
    String studentCode;
    String facultyGroupCode;
}
