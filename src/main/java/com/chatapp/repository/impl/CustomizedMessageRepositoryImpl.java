package com.chatapp.repository.impl;

import com.chatapp.dto.Pagination;
import com.chatapp.entity.MessageEntity;
import com.chatapp.repository.CustomizedMessageRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomizedMessageRepositoryImpl implements CustomizedMessageRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<MessageEntity> findBySenderOrReceiver(Long senderId, Long receiverId) {
        String sql = buildBaseQuery(senderId, receiverId, null).toString();

        Query query = entityManager.createQuery(sql)
                .setParameter(1, senderId)
                .setParameter(2, receiverId);

        return query.getResultList();
    }

    @Override
    public List<MessageEntity> findBySenderOrReceiver(Long senderId, Long receiverId, Pagination pagination) {
        StringBuilder sql = buildBaseQuery(senderId, receiverId, pagination);

        Query query = getQuery(senderId, receiverId, sql, pagination);

        return query.getResultList();
    }

    private Query getQuery(Long senderId, Long receiverId, StringBuilder sql, Pagination pagination) {
        Query query = entityManager.createQuery(sql.toString());

        int curentIndex = 1;
        if (senderId != null) {
            query.setParameter(curentIndex, senderId);
            curentIndex++;
        }

        if (receiverId != null) {
            query.setParameter(curentIndex, receiverId);
            curentIndex++;
        }

        if (pagination != null && pagination.getLimit() != null) {
            query.setParameter(curentIndex, pagination.getLimit());
            curentIndex++;
        }

        if (pagination != null && pagination.getOffset() != null) {
            query.setParameter(curentIndex, pagination.getOffset());
        }
        return query;
    }

    private static StringBuilder buildBaseQuery(Long senderId, Long receiverId, Pagination pagination) {
        int currentParamIndex = 1;

        StringBuilder sql = new StringBuilder("SELECT m FROM MessageEntity m")
                .append("\nJOIN m.conversations as c")
                .append("\nJOIN c.sender as s")
                .append("\nJOIN c.receiver as r")
                .append("\nWHERE 1 = 1");

        if (senderId != null && receiverId != null) {
            sql.append("\nAND (s.id = ?1 AND r.id = ?2)");
            currentParamIndex += 2;
        }
        
        sql.append("\nORDER BY m.createdAt DESC");


        if (pagination != null && pagination.getLimit() != null) {
            sql.append("\nLIMIT ?").append(currentParamIndex);
            currentParamIndex++;
        }

        if (pagination != null && pagination.getOffset() != null) {
            sql.append("\nOFFSET ?").append(currentParamIndex);
        }

        return sql;
    }
}
