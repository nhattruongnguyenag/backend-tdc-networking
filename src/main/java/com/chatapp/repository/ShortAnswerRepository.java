package com.chatapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatapp.entity.ShortAnswerEntity;

@Repository
public interface ShortAnswerRepository extends JpaRepository<ShortAnswerEntity, Long> {
    ShortAnswerEntity findOneByUser_Id(Long id);
    ShortAnswerEntity findOneByUser_IdAndQuestion_Id(Long id , Long questionId);
    List<ShortAnswerEntity> findAllByQuestion_Id(Long id);
}
