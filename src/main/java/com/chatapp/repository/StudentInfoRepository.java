package com.chatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatapp.entity.StudentInfoEntity;


@Repository
public interface StudentInfoRepository extends JpaRepository<StudentInfoEntity, Long> {
    StudentInfoEntity findOneByUser_Id(Long id);
}
