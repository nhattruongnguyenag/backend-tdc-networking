package com.chatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatapp.entity.PostApprovalLogEntity;

@Repository
public interface PostApprovalLogRepository extends JpaRepository<PostApprovalLogEntity, Long> {

}
