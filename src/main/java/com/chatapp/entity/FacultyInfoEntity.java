package com.chatapp.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "falcuty_infos")
@Data
public class FacultyInfoEntity extends BaseEntity {

    @Column(name = "faculty_name", nullable = false)
    private String facultyName;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}
