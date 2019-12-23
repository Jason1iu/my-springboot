package com.example.myspringboot.service;

import java.util.List;
import com.example.myspringboot.entity.RolePermission;

/**
 * 
 * @author LiuJie
 *
 */
public interface PermissionService {
	List<RolePermission> getRolePermissions();
}
