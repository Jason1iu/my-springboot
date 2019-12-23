package com.example.myspringboot.service;

import java.util.List;

import com.example.myspringboot.entity.Role;

public interface RoleService {
    List<Role> getRolesByUserId(Long userId);
}
