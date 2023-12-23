package com.chatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatapp.entity.MajorEntity;

@Repository
public interface MajorRepository extends JpaRepository<MajorEntity, Long> {
    MajorEntity findOneById(Long id);
}
