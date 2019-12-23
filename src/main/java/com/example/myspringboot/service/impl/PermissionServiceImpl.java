package com.example.myspringboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.example.myspringboot.entity.RolePermission;
import com.example.myspringboot.service.PermissionService;

/**
 * 
 * @author LiuJie
 *
 */
@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<RolePermission> getRolePermissions() {
		String sql = "SELECT A.name AS roleName,C.url FROM sys_role AS A LEFT JOIN sys_permission_role B ON A.id=B.sys_role_id LEFT JOIN sys_permission AS C ON B.sys_permission_id=C.id";
		RowMapper<RolePermission> rowMapper = BeanPropertyRowMapper.newInstance(RolePermission.class);
		List<RolePermission> list = this.jdbcTemplate.query(sql, rowMapper);
		return list;
	}

}
