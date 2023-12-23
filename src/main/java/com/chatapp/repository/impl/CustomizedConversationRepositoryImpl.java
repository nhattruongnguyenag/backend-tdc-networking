package com.chatapp.repository.impl;

import com.chatapp.entity.ConversationEntity;
import com.chatapp.repository.CustomizedConversationRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomizedConversationRepositoryImpl implements CustomizedConversationRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ConversationEntity> findBySenderAndReceiver(long senderId, long receiverId) {
        String sql = new StringBuilder("SELECT c FROM ConversationEntity c")
                .append("\nJOIN c.sender as s")
                .append("\nJOIN c.receiver as r")
                .append("\nWHERE (s.id = ?1 OR r.id = ?2)")
                .append("\nAND (s.id = ?3 OR r.id = ?4)")
                .append("\nORDER BY c.createdAt ASC").toString();

        Query query = entityManager.createQuery(sql, ConversationEntity.class)
                .setParameter(1, senderId)
                .setParameter(2, senderId)
                .setParameter(3, receiverId)
                .setParameter(4, receiverId);

        return query.getResultList();
    }

    @Override
    public long countDistinctBySender_IdAndMessages_Status(long senderId, long status) {
        String sql = new StringBuilder("SELECT count(distinct c) FROM ConversationEntity c")
                .append("\nJOIN c.sender as s")
                .append("\nJOIN c.messages as m")
                .append("\nJOIN m.receiver as mr")
                .append("\nWHERE s.id = ").append(senderId)
                .append("\nAND m.status = ").append(status)
                .append("\nAND mr.id = ").append(senderId).toString();

        Query query = entityManager.createQuery(sql, Long.class);

        return (long) query.getSingleResult();
    }
}
