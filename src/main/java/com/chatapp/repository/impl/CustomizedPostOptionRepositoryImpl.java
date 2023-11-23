package com.chatapp.repository.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chatapp.entity.PostEntity;
import com.chatapp.repository.CustomizedPostOptionRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class CustomizedPostOptionRepositoryImpl implements CustomizedPostOptionRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<PostEntity> findByOptions(String groupCode, String status, String facultyCode) {
        StringBuilder sql = new StringBuilder("SELECT p FROM PostEntity p");

        buildJoinQuery(sql, groupCode, status, facultyCode);
        sql.append("\nWHERE 1 = 1");
        buildWhereQuery(sql, groupCode, status, facultyCode);

        sql.append("\nORDER BY p.createdAt DESC");

        Query query = entityManager.createQuery(sql.toString());
        return query.getResultList();
    }

    private void buildJoinQuery(StringBuilder sql, String groupCode, String status, String facultyCode) {
        if (groupCode != null) {
            sql.append("\nJOIN p.group as g");
        }

        // if (facultyCode != null) {
        //     sql.append("\nJOIN UserEntity as u ON p.user.id = u.id");
        // }
    }

    private void buildWhereQuery(StringBuilder sql, String groupCode, String status, String facultyCode) {
        // if (groupCode != null) {
        //     sql.append("\nAND g.code = '" + groupCode.toString() + "'");
        // }

        if (status != null) {
            sql.append("\nAND status = " + status);
        }

        // if (facultyCode != null) {
        //     sql.append("\nAND u.code = 'student_12");
        // }
    }

}
