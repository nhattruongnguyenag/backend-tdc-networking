package com.chatapp.repository.impl;

import com.chatapp.dto.request.PostSearchRequestDTO;
import com.chatapp.entity.PostEntity;
import com.chatapp.repository.CustomizedPostRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomizedPostRepositoryImpl implements CustomizedPostRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<PostEntity> findPosts(PostSearchRequestDTO dto) {
        StringBuilder finalQuery = new StringBuilder("SELECT p FROM PostEntity p");

        StringBuilder whereQuery = new StringBuilder();
        StringBuilder joinQuery = new StringBuilder();

        buildJoinQuery(dto, joinQuery, whereQuery);
        buildWhereQuery(dto, whereQuery);

        finalQuery.append(joinQuery);
        finalQuery.append("\nWHERE 1 = 1");
        finalQuery.append(whereQuery);
        finalQuery.append("\nORDER BY p.updatedAt DESC");

        Query query = entityManager.createQuery(finalQuery.toString(), PostEntity.class);
        return query.getResultList();
    }

    private void buildJoinQuery(PostSearchRequestDTO dto, StringBuilder joinQuery, StringBuilder whereQuery) {
        if (isValid(dto.getOwnerFaculty())) {
            joinQuery.append("\nJOIN p.user as u");
            joinQuery.append("\nJOIN u.studentInfo as st");
            joinQuery.append("\nJOIN st.faculty as facultyInfo");
            joinQuery.append("\nJOIN facultyInfo.user as f");
            whereQuery.append("\nAND f.code LIKE ").append("'%").append(dto.getOwnerFaculty()).append("%'");
        }

        if (isValid(dto.getUserId()) && !isValid(dto.getOwnerFaculty())) {
            joinQuery.append("\nJOIN p.user as u");
        }

        if (isValid(dto.getUserId())) {
            whereQuery.append("\nAND u.id = ").append(dto.getUserId());
        }

        if (isValid(dto.getGroup())) {
            joinQuery.append("\nJOIN p.group as g");
            whereQuery.append("\nAND g.code LIKE ").append("'%").append(dto.getGroup()).append("%'");
        }
    }

    private void buildWhereQuery(PostSearchRequestDTO dto, StringBuilder whereQuery) {
        if (isValid(dto.getStatus())) {
            whereQuery.append("\nAND p.status = ").append(dto.getStatus());
        }

        if (isValid(dto.getActive())) {
            whereQuery.append("\nAND p.active = ").append(dto.getActive());
        }

        if (isValid(dto.getPostId())) {
            whereQuery.append("\nAND p.id = ").append(dto.getPostId());
        }

        if (isValid(dto.getType())) {
            whereQuery.append("\nAND p.type LIKE ").append("'%").append(dto.getType()).append("%'");
        }
    }

    private boolean isValid(Object str) {
        return str != null;
    }
}
