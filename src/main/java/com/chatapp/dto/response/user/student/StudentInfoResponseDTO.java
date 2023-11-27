package com.chatapp.dto.response.user.student;

import com.chatapp.dto.response.user.UserInfoResponseDTO;

import lombok.*;

@Data
public class StudentInfoResponseDTO extends UserInfoResponseDTO {
    String facultyName;
    String major;
    String studentCode;
    String facultyGroupCode;
    Long facultyGroupId;
}
