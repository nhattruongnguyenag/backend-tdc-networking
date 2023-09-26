package com.chatapp.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.chatapp.dto.response.RoleResponeDTO;
import com.chatapp.service.RoleService;

@RestController
@RequestMapping("/api")
public class RoleAPI {
    @Autowired
    RoleService roleService;

    @GetMapping({ "roles", "roles/" })
    public List<RoleResponeDTO> findAll() {
        return roleService.findAll();
    }
}
