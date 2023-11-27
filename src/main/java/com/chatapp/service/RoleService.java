package com.chatapp.service;



import java.util.List;

import com.chatapp.dto.response.role.RoleResponseDTO;

public interface RoleService {
    List<RoleResponseDTO> findAll();
}