package com.chatapp.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "student_infos")
public class StudentInfoEntity extends BaseEntity {
    @Column(name = "student_code", nullable = false)
    private String studentCode;

    @ManyToOne
    @JoinColumn(name = "major_id", nullable = true)
    private MajorEntity major;

    @ManyToOne
    @JoinColumn(name = "faculty_id", nullable = true)
    private FacultyInfoEntity faculty;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }


    public MajorEntity getMajor() {
        return this.major;
    }

    public void setMajor(MajorEntity major) {
        this.major = major;
    }

    public FacultyInfoEntity getFaculty() {
        return this.faculty;
    }

    public void setFaculty(FacultyInfoEntity faculty) {
        this.faculty = faculty;
    }
}
