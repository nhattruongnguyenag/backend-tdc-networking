package com.chatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatapp.entity.BusinessesInfoEntity;

@Repository
public interface BusinessInfoRepository extends JpaRepository<BusinessesInfoEntity, Long> {
    BusinessesInfoEntity findOneByUser_Id(Long id);
}
