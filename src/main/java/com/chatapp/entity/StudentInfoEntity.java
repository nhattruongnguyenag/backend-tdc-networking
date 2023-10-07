package com.chatapp.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "student_infos")
public class StudentInfoEntity extends BaseEntity {

    @Column(name = "student_code", nullable = false)
    private String studentCode;

    @Column(name = "major", nullable = false)
    private String major;

    @Column(name = "faculty_name", nullable = false)
    private String facultyName;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
