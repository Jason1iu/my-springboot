package com.example.myspringboot.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/** 
* @author LiuJie 
* @date 2019年12月23日 下午4:59:49 
* 资源服务器配置
* 
*/
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	/**
	 * 这里设置需要token验证的url
	 * 这些url需要在WebSecurityConfigurerAdapter中排掉
	 * 否则进入WebSecurityConfigurerAdapter，进行的是basic auth或表单认证，而不是token认证
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.requestMatchers().antMatchers("/hello")
			.and()
			.authorizeRequests()
			.antMatchers("/hello").authenticated();
	}
}
