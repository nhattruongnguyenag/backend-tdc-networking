package com.chatapp.repository;

import com.chatapp.entity.DeviceTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceTokenRepository extends JpaRepository<DeviceTokenEntity, Long> {
    DeviceTokenEntity findByToken(String token);
}
