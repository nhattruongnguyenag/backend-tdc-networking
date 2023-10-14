package com.chatapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "majors")
public class MajorEntity extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

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

}