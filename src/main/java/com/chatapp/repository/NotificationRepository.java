package com.chatapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatapp.entity.NotificationEntity;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
    List<NotificationEntity> findByUser_IdOrderByUpdatedAtDesc(Long id);
    List<NotificationEntity> findByUser_IdAndStatusOrderByUpdatedAtDesc(Long id, Long status);
    List<NotificationEntity>findByContentContains(String content);
    NotificationEntity findByIdAndUser_Id(Long id, Long userId);
}
