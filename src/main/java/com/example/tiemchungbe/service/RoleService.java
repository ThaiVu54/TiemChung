package com.example.tiemchungbe.service;

import com.example.tiemchungbe.model.entity.Role;
import com.example.tiemchungbe.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public List<Role> findAllRole() {
        return roleRepository.findAll();
    }

    @Override
    public void setDefaultRole(int accountId, Integer roleId) {
        roleRepository.setDefaultRole(accountId, roleId);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAllRole();
    }
}
