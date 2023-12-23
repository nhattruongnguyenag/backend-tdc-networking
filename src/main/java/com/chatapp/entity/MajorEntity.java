package com.chatapp.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "majors")
public class MajorEntity extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "code", nullable = false , unique = true)
    private String code;

    @ManyToOne
    @JoinColumn(name = "faculty_id", nullable = false)
    private FacultyInfoEntity faculty;

    @OneToMany(mappedBy = "major", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<StudentInfoEntity> studentsInMajor = new ArrayList<>();

    public List<StudentInfoEntity> getStudentsInMajor() {
        return this.studentsInMajor;
    }

    public void setStudentsInMajor(List<StudentInfoEntity> studentsInMajor) {
        this.studentsInMajor = studentsInMajor;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FacultyInfoEntity getFaculty() {
        return this.faculty;
    }

    public void setFaculty(FacultyInfoEntity faculty) {
        this.faculty = faculty;
    }


    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}