package com.chatapp.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chatapp.entity.PostEntity;

@Repository
public interface CustomizedPostOptionRepository {
    List<PostEntity> findByOptions(String groupCode, String status, String facultyCode);
}
