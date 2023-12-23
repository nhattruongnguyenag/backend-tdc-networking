package com.chatapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatapp.entity.NormalPostEntity;

@Repository
public interface NormalPostRepository extends JpaRepository<NormalPostEntity, Long> {
    NormalPostEntity findOneByPost_Id(Long id);
    List<NormalPostEntity> findAllByContentContains(String content);
}
