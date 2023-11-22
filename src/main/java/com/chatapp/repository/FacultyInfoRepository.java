package com.chatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatapp.entity.FacultyInfoEntity;


@Repository
public interface FacultyInfoRepository extends JpaRepository<FacultyInfoEntity, Long> {
    FacultyInfoEntity findOneByUser_Id(Long id);
    FacultyInfoEntity findOneById(Long id);
}
