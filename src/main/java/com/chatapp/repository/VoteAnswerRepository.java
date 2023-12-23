package com.chatapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatapp.entity.VoteAnswerEntity;

@Repository
public interface VoteAnswerRepository extends JpaRepository<VoteAnswerEntity, Long> {
    VoteAnswerEntity findOneById(Long id);
    List<VoteAnswerEntity> findAllByQuestion_Id(Long questionId);
}
