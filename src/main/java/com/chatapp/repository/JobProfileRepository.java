package com.chatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatapp.entity.JobProfileEntity;

@Repository
public interface JobProfileRepository extends JpaRepository<JobProfileEntity,Long> {
}
