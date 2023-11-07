package com.chatapp.dto.response;


import java.util.List;

import lombok.*;

@Data
public class FacultyInfoResponseDTO extends UserInfoResponseDTO{
    List<MajorResponseDTO> majors;
    String facultyGroupCode;
    Long facultyGroupId;
}
