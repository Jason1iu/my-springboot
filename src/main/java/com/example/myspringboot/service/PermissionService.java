package com.example.myspringboot.service;

import java.util.List;
import com.example.myspringboot.entity.RolePermission;

/**
 * 
 * @author LiuJie
 *
 */
public interface PermissionService {
//	 @Select( "SELECT A.NAME AS roleName,C.url FROM role AS A LEFT JOIN role_permission B ON A.id=B.role_id LEFT JOIN permission AS C ON B.permission_id=C.id" )
	List<RolePermission> getRolePermissions();
}
