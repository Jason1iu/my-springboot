package com.example.myspringboot.entity;

import java.io.Serializable;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * 
 * @author LiuJie
 *
 */
public class User implements UserDetails, Serializable {
	private static final long serialVersionUID = 1L;

	private long id;
	private String username;
	private String password;

	private List<Role> authorities;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public List<Role> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Role> authorities) {
		this.authorities = authorities;
	}

	/**
	 * 用户账号是否过期
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * 用户账号是否被锁定
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * 用户密码是否过期
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * 用户是否可用
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}

}
