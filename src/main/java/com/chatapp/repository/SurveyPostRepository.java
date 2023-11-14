package com.chatapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatapp.entity.SurveyPostEntity;

@Repository
public interface SurveyPostRepository extends JpaRepository<SurveyPostEntity, Long> {
    SurveyPostEntity findOneByPost_Id(Long id);
    SurveyPostEntity findOneById(Long id);
    List<SurveyPostEntity> findAllByTitleContains(String content);
}
