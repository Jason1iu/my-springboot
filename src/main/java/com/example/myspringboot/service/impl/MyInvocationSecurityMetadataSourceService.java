package com.example.myspringboot.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import com.example.myspringboot.entity.RolePermission;
import org.springframework.security.access.SecurityConfig;
import com.example.myspringboot.service.PermissionService;

/**
 * 
 * @author LiuJie
 *
 */
@Service
public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

	@Autowired
	private PermissionService permissionService;

	/**
	 * 每一个资源所需要的角色 Collection<ConfigAttribute>决策器会用到
	 */
	private static HashMap<String, Collection<ConfigAttribute>> map = null;

	/**
	 * 返回请求的资源需要的角色
	 * 当接收到一个http请求时，filterSecurityInterceptor会调用的方法，参数object是一个包含url信息的HttpServiceRequest实例，这个方法要返回请求改url的所需要的所有权限集合
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		if (null == map) {
			this.loadResourceDefine();
		}
		// object中包含用户请求的request信息
		HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
		for (Iterator<String> it = map.keySet().iterator(); it.hasNext();) {
			String url = it.next();
			if (new AntPathRequestMatcher(url).matches(request)) {
				return map.get(url);
			}
		}
		return null;
	}

	// Spring容器启动时自动调用，一般把所有请求与权限的对应关系也要在这个方法里初始化，保存在一个属性变量里
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	// 该类是否能够为指定的方法调用或Web请求提供ConfigAttributes
	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

	public void loadResourceDefine() {
		map = new HashMap<>(16);
		// 权限资源和角色对应的表，就是角色权限中间表
		List<RolePermission> rolePermissions = this.permissionService.getRolePermissions();

		// 某个资源可以被哪些角色访问
		for (RolePermission rolePermission : rolePermissions) {
			String url = rolePermission.getUrl();
			String roleName = rolePermission.getRoleName();
			ConfigAttribute role = new SecurityConfig(roleName);
			if (map.containsKey(url)) {
				map.get(url).add(role);
			} else {
				List<ConfigAttribute> list = new ArrayList<>();
				list.add(role);
				map.put(url, list);
			}
		}
	}

}
