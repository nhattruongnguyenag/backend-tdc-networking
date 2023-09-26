package com.chatapp.service;



import java.util.List;

import com.chatapp.dto.response.RoleResponeDTO;

public interface RoleService {
    List<RoleResponeDTO> findAll();
}