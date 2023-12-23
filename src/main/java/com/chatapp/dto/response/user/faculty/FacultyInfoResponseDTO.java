package com.chatapp.dto.response.user.faculty;


import com.chatapp.dto.response.major.MajorResponseDTO;
import com.chatapp.dto.response.user.UserInfoResponseDTO;

import java.util.List;

public class FacultyInfoResponseDTO extends UserInfoResponseDTO{
    List<MajorResponseDTO> majors;
    String facultyGroupCode;
    Long facultyGroupId;

    public List<MajorResponseDTO> getMajors() {
        return majors;
    }

    public void setMajors(List<MajorResponseDTO> majors) {
        this.majors = majors;
    }

    public String getFacultyGroupCode() {
        return facultyGroupCode;
    }

    public void setFacultyGroupCode(String facultyGroupCode) {
        this.facultyGroupCode = facultyGroupCode;
    }

    public Long getFacultyGroupId() {
        return facultyGroupId;
    }

    public void setFacultyGroupId(Long facultyGroupId) {
        this.facultyGroupId = facultyGroupId;
    }
}
