package com.example.myspringboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.myspringboot.entity.Role;
import com.example.myspringboot.entity.User;
import com.example.myspringboot.service.RoleService;
import com.example.myspringboot.service.UserService;

/**
 * 
 * @author LiuJie
 *
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 查数据库
		User user = this.userService.loadUserByUsername(username);
		if (null != user) {
			List<Role> roles = this.roleService.getRolesByUserId(user.getId());
			user.setAuthorities(roles);
		}
		return user;
	}

}
