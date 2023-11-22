package com.chatapp.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "falcuty_infos")
public class FacultyInfoEntity extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<MajorEntity> majors = new ArrayList<>();

    @OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<StudentInfoEntity> studentsInFaculty = new ArrayList<>();

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<MajorEntity> getMajors() {
        return this.majors;
    }

    public void setMajors(List<MajorEntity> majors) {
        this.majors = majors;
    }

    public List<StudentInfoEntity> getStudentsInFaculty() {
        return this.studentsInFaculty;
    }

    public void setStudentsInFaculty(List<StudentInfoEntity> studentsInFaculty) {
        this.studentsInFaculty = studentsInFaculty;
    }

}
