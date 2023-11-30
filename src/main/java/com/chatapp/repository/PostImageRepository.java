package com.chatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatapp.entity.PostImageEntity;

@Repository
public interface PostImageRepository extends JpaRepository<PostImageEntity, Long> {

}
