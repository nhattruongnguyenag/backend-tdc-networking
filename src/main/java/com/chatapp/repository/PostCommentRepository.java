package com.chatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatapp.entity.PostCommentEntity;

@Repository
public interface PostCommentRepository extends JpaRepository<PostCommentEntity, Long> {
    PostCommentEntity findOneById(Long id);
    PostCommentEntity findByIdAndUser_IdAndPost_Id(Long id,Long userId , Long postId);
    PostCommentEntity findOneByParentComment_Id(Long parentId);
}
