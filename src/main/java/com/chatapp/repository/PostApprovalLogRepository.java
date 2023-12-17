package com.chatapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatapp.entity.PostApprovalLogEntity;

@Repository
public interface PostApprovalLogRepository extends JpaRepository<PostApprovalLogEntity, Long> {
    List<PostApprovalLogEntity> findAllByPost_Id(Long postId);
    PostApprovalLogEntity findOneById(Long Id);
}
