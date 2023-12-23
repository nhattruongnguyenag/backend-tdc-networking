package com.chatapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.chatapp.converter.response.role.RoleResponseConverter;
import com.chatapp.dto.response.role.RoleResponseDTO;
import com.chatapp.repository.RoleRepository;
import com.chatapp.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleResponseConverter roleResponeConverter;

    @Override
    public List<RoleResponseDTO> findAll() {
        return roleResponeConverter.toDTOGroup(roleRepository.findAll());
    }

}
