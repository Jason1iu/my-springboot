package com.example.myspringboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.example.myspringboot.entity.Role;
import com.example.myspringboot.service.RoleService;

/**
 * @author LiuJie
 * @date 2019年12月23日 上午11:27:48
 * 
 */
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Role> getRolesByUserId(Long userId) {
		String sql = "SELECT A.id,A.name FROM sys_role A LEFT JOIN sys_user_role B ON A.id=B.sys_role_id WHERE B.sys_user_id=?";
		RowMapper<Role> rowMapper = BeanPropertyRowMapper.newInstance(Role.class);
		List<Role> list = this.jdbcTemplate.query(sql, new Object[] { userId }, rowMapper);
		return list;
	}

}
