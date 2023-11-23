package com.chatapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatapp.entity.PostEntity;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
    PostEntity findOneById(Long id);
    PostEntity findOneByIdAndUser_Id(Long id , Long userId);
    List<PostEntity> findAllByOrderByUpdatedAtAsc();
    List<PostEntity> findAllByOrderByUpdatedAtDesc();
    List<PostEntity> findAllByGroup_IdOrderByUpdatedAtDesc(Long groupId);
    List<PostEntity> findAllByGroup_IdAndStatusOrderByUpdatedAtDesc(Long groupId , Long status);
    List<PostEntity> findAllByUser_IdAndGroup_IdOrderByUpdatedAtDesc(Long userId,Long groupId);
    List<PostEntity> findAllByUser_IdAndGroup_CodeOrderByUpdatedAtDesc(Long userId,String code);
    List<PostEntity> findAllByUser_Roles_CodeOrderByUpdatedAtDesc(String code);
    List<PostEntity> findAllByUser_IdAndTypeOrderByUpdatedAtDesc(Long userId , String type);
    List<PostEntity> findAllByUser_IdOrderByUpdatedAtDesc(Long userId);
}
