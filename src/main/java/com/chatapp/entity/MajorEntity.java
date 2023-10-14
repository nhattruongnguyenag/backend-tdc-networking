package com.chatapp.entity;

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