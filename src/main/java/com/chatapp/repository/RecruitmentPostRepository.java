package com.chatapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatapp.entity.RecruitmentPostEntity;

@Repository
public interface RecruitmentPostRepository extends JpaRepository<RecruitmentPostEntity, Long> {
    RecruitmentPostEntity findOneByPost_Id(Long id);
    List<RecruitmentPostEntity> findAllByTitleContains(String content);
}
