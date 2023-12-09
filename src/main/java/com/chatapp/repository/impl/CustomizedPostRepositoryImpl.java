package com.chatapp.repository.impl;

import com.chatapp.dto.request.post.PostSearchRequestDTO;
import com.chatapp.entity.PostEntity;
import com.chatapp.enums.PostType;
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
        finalQuery.append("\nORDER BY p.updatedAt DESC ");

        if (dto.getLimit() != null) {
            finalQuery.append("LIMIT ").append(dto.getLimit());
        }

        if (dto.getOffset() != null) {
            finalQuery.append("OFFSET ").append(dto.getOffset());
        }

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

        if (isValid(dto.getFaculty())) {
            joinQuery.append("\nJOIN p.user as us");
            joinQuery.append("\nJOIN us.falcutyInfo as ft");
            joinQuery.append("\nJOIN ft.user as ftu");
            whereQuery.append("\nAND ftu.code LIKE ").append("'%").append(dto.getFaculty()).append("%'");
        }

        if (dto.getUserId() != null && !isValid(dto.getOwnerFaculty())) {
            joinQuery.append("\nJOIN p.user as u");
        }

        if (dto.getUserId() != null) {
            whereQuery.append("\nAND u.id = ").append(dto.getUserId());
        }

        if (isValid(dto.getGroup())) {
            if (dto.getGroup().equalsIgnoreCase("none")) {
                whereQuery.append("\nAND p.group = " + null);
            } else {
                joinQuery.append("\nJOIN p.group as g");
                whereQuery.append("\nAND g.code LIKE ").append("'%").append(dto.getGroup()).append("%'");
            }
        }

        if (isValid(dto.getSearch())) {
            if (dto.getType().equals(PostType.NORMAL.getName())) {
                joinQuery.append("\nJOIN p.normalPost as normal");
                whereQuery.append("\nAND normal.content LIKE ").append("'%").append(dto.getSearch()).append("%'");
            }
            if (dto.getType().equals(PostType.SURVEY.getName())) {
                joinQuery.append("\nJOIN p.surveyPost as survey");
                whereQuery.append("\nAND survey.title LIKE ").append("'%").append(dto.getSearch()).append("%'");
            }
            if (dto.getType().equals(PostType.RECRUIMENT.getName())) {
                joinQuery.append("\nJOIN p.recruitmentPost as recruitment");
                whereQuery.append("\nAND recruitment.title LIKE ").append("'%").append(dto.getSearch())
                        .append("%'");
            }
        }
    }

    private void buildWhereQuery(PostSearchRequestDTO dto, StringBuilder whereQuery) {
        if (dto.getStatus() != null) {
            whereQuery.append("\nAND p.status = ").append(dto.getStatus());
        }

        if (dto.getActive() != null) {
            whereQuery.append("\nAND p.active = ").append(dto.getActive());
        }

        if (dto.getPostId() != null) {
            whereQuery.append("\nAND p.id = ").append(dto.getPostId());
        }

        if (isValid(dto.getType())) {
            whereQuery.append("\nAND p.type LIKE ").append("'%").append(dto.getType()).append("%'");
        }
    }

    private boolean isValid(String str) {
        return str != null && !str.isBlank();
    }
}
