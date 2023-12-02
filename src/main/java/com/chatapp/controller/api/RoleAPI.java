package com.chatapp.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.chatapp.dto.response.role.RoleResponseDTO;
import com.chatapp.service.RoleService;

@RestController
@RequestMapping("/api")
public class RoleAPI {
    @Autowired
    RoleService roleService;

    //////////////////
    //Get
    //////////////////
    @GetMapping({ "roles", "roles/" })
    public List<RoleResponseDTO> findAll() {
        return roleService.findAll();
    }
}
