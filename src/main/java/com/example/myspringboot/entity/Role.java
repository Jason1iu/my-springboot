package com.example.myspringboot.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * 
 * @author LiuJie
 *
 */
public class Role implements GrantedAuthority {

	private static final long serialVersionUID = 1L;
	private long id;
	private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getAuthority() {
		return name;
	}

}
