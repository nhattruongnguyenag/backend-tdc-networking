package com.chatapp.service;



import java.util.List;

import com.chatapp.dto.response.RoleResponseDTO;

public interface RoleService {
    List<RoleResponseDTO> findAll();
}