package com.example.myspringboot.service;

import com.example.myspringboot.entity.User;

public interface UserService {
	User loadUserByUsername(String username);
}
