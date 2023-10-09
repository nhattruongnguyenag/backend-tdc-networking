package com.chatapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatapp.entity.PostEntity;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
    PostEntity findOneById(Long id);
    List<PostEntity> findAllByOrderByUpdatedAtAsc();
    List<PostEntity> findAllByOrderByUpdatedAtDesc();
}
