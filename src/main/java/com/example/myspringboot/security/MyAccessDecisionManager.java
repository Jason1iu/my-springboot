package com.example.myspringboot.security;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

/**
 * @author LiuJie
 * @date 2019年12月23日 上午10:43:05 
 * 决策器:MyAccessDecisionManager 类实现了AccessDecisionManager接口，
 * AccessDecisionManager是由AbstractSecurityInterceptor调用的，
 * 它负责鉴定用户是否有访问对应资源（方法或URL）的权限
 */
@Service
public class MyAccessDecisionManager implements AccessDecisionManager {

	/**
	 * 通过传递的参数来决定用户是否有访问对应受保护对象的权限
	 * 
	 * @param authentication   包含了当前的用户信息，包括拥有的权限，这里的权限来源就是前面登录时userDetailsService中设置的authorities
	 * @param object           就是FilterInvocation对象，可以得到request等web资源
	 * @param configAttributes 是本次访问需要的权限
	 */
	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		if (null == configAttributes || 0 >= configAttributes.size()) {
			return;
		} else {
			String needRole;
			for (Iterator<ConfigAttribute> iter = configAttributes.iterator(); iter.hasNext();) {
				needRole = iter.next().getAttribute();
				for (GrantedAuthority ga : authentication.getAuthorities()) {
					if (needRole.trim().equals(ga.getAuthority().trim())) {
						return;
					}
				}
			}
			throw new AccessDeniedException("当前访问没有权限！");
		}
	}

	/**
	 * 表示此AccessDecisionManager是否能够处理传递的ConfigAttribute呈现的授权请求
	 */
	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	/**
	 * 表示当前AccessDecisionNagefer实现是否能够为指定的安全对象（方法调用或web请求）提供访控制决策
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
