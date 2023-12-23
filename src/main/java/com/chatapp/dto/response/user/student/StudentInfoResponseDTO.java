package com.chatapp.dto.response.user.student;

import com.chatapp.dto.response.user.UserInfoResponseDTO;

public class StudentInfoResponseDTO extends UserInfoResponseDTO {
    String facultyName;
    String major;
    String studentCode;
    String facultyGroupCode;
    Long facultyGroupId;

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
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
