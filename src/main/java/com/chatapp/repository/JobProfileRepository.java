package com.chatapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatapp.entity.JobProfileEntity;

@Repository
public interface JobProfileRepository extends JpaRepository<JobProfileEntity,Long> {
    List<JobProfileEntity> findAllByPost_Id(Long postId);
    JobProfileEntity findOneById(Long id);
    JobProfileEntity findOneByPost_IdAndUser_Id(Long postId, Long userId);
}
