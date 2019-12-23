package com.example.myspringboot.service;

import java.util.List;

import com.example.myspringboot.entity.Role;

public interface RoleService {
//    @Select( "SELECT A.id,A.name FROM role A LEFT JOIN user_role B ON A.id=B.role_id WHERE B.user_id=${userId}" )
    List<Role> getRolesByUserId(Long userId);
}
