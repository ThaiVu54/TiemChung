package com.example.tiemchungbe.service;

import com.example.tiemchungbe.model.entity.Role;

import java.util.List;

public interface IRoleService {
    List<Role> findAllRole();
    void setDefaultRole(int accountId , Integer roleId);
    List<Role> getAllRoles();
}
