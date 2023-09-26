package com.chatapp.dto.response;

import com.chatapp.dto.UserDTO;

import lombok.*;

@Data
public class StudentInfoResponeDTO {
    UserDTO user;
    String facultyName;
    String major;
    String studentCode;
}
