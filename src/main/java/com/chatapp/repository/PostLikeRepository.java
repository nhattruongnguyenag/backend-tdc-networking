package com.chatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatapp.entity.PostLikeEntity;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLikeEntity, Long> {
    PostLikeEntity findByPost_IdAndUser_Id(Long postId, Long userId);
}
