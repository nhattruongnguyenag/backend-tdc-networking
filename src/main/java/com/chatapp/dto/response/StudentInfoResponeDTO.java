package com.chatapp.dto.response;

import com.chatapp.dto.BaseDTO;

import lombok.*;

@Data
public class StudentInfoResponeDTO extends BaseDTO {
    UserInfoResponseDTO user;
    String facultyName;
    String major;
    String studentCode;
}
