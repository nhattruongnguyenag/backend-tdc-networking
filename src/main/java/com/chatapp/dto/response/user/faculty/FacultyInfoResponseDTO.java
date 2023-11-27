package com.chatapp.dto.response.user.faculty;


import java.util.List;

import com.chatapp.dto.response.major.MajorResponseDTO;
import com.chatapp.dto.response.user.UserInfoResponseDTO;

import lombok.*;

@Data
public class FacultyInfoResponseDTO extends UserInfoResponseDTO{
    List<MajorResponseDTO> majors;
    String facultyGroupCode;
    Long facultyGroupId;
}
