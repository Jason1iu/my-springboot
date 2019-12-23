package com.example.myspringboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.example.myspringboot.entity.User;
import com.example.myspringboot.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public User loadUserByUsername(String username) {
		String sql = "select id , username , password from sys_user where username=?";
		RowMapper<User> rowMapper = BeanPropertyRowMapper.newInstance(User.class);
		List<User> users = this.jdbcTemplate.query(sql, new Object[] { username }, rowMapper);
		User user = null;
		if (!users.isEmpty()) {
			user = users.get(0);
		}
		return user;
	}

}
