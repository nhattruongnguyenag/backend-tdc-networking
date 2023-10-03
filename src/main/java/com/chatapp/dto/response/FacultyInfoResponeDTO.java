package com.chatapp.dto.response;

import com.chatapp.dto.BaseDTO;

import lombok.*;

@Data
public class FacultyInfoResponeDTO extends BaseDTO{
    UserInfoResponseDTO user;
    String facultyName;
}
