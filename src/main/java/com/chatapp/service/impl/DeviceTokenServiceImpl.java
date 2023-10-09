package com.chatapp.service.impl;

import com.chatapp.commond.MessageResponseData;
import com.chatapp.dto.request.DeviceTokenRequestDTO;
import com.chatapp.entity.DeviceTokenEntity;
import com.chatapp.entity.UserEntity;
import com.chatapp.repository.DeviceTokenRepository;
import com.chatapp.repository.UserRepository;
import com.chatapp.service.DeviceTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeviceTokenServiceImpl implements DeviceTokenService {
    @Autowired
    private DeviceTokenRepository deviceTokenRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean saveUserDeviceToken(DeviceTokenRequestDTO requestDTO) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(requestDTO.getUserId());

        if (userEntityOptional.isPresent()) {
            if (deviceTokenRepository.findByToken(requestDTO.getDeviceToken()) == null) {
                DeviceTokenEntity deviceTokenEntity = new DeviceTokenEntity();
                deviceTokenEntity.setToken(requestDTO.getDeviceToken());
                deviceTokenEntity.setUser(userEntityOptional.get());
                deviceTokenRepository.save(deviceTokenEntity);
            }

            return true;
        }

        return false;
    }
}
