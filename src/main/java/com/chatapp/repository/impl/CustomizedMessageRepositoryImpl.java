package com.chatapp.repository.impl;

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
        String sql = new StringBuilder("SELECT m FROM MessageEntity m")
                .append("\nJOIN m.conversations as c")
                .append("\nJOIN c.sender as s")
                .append("\nJOIN c.receiver as r")
                .append("\nWHERE (s.id = ?1 AND r.id = ?2)")
                .append("\nORDER BY m.createdAt DESC").toString();

        Query query = entityManager.createQuery(sql)
                .setParameter(1, senderId)
                .setParameter(2, receiverId);

        return query.getResultList();
    }

    // @Override
    // public List<MessageEntity> findBySenderOrReceiver(Long senderId, Long receiverId) {
    //     StringBuilder sql = new StringBuilder("SELECT m FROM MessageEntity m");


    //     buildJoinQuery(sql, senderId, receiverId);
    //     sql.append("\nWHERE 1 = 1")
    //     buildWhereQuery(sql, senderId, receiverId);

    //     sql.append("\nORDER BY m.createdAt DESC").toString();

    //     Query query = entityManager.createQuery(sql.toString())
    //             .setParameter(1, senderId)
    //             .setParameter(2, receiverId);

    //     return query.getResultList();
    // }

    // private void buildJoinQuery(StringBuilder sql, Long senderId, Long receiverId) {
    //     if (senderId != null) {
    //         sql.append("\nJOIN m.conversations as c");
    //     }

    //     if (receiverId != null) {
    //         sql.append("\nJOIN c.sender as s");
    //     }
    // }

    // private void buildWhereQuery(StringBuilder sql, Long senderId, Long receiverId) {
    //     if (senderId != null) {
    //         sql.append("\nAND senderId = " + senderId.toString());
    //     }

    //     if (receiverId != null) {
    //         sql.append("\nAND receiverId = " + receiverId.toString());
    //     }
    // }

}
