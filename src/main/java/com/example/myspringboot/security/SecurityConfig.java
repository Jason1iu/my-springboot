package com.example.myspringboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.DigestUtils;

import com.example.myspringboot.service.impl.MyUserDetailsService;

/**
 * 
 * @author LiuJie
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// 校验用户
		auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordEncoder() {
			// 对密码进行加密
			@Override
			public String encode(CharSequence rawPassword) {
//				System.out.println(rawPassword.toString());
				String md5String = DigestUtils.md5DigestAsHex(rawPassword.toString().getBytes());
//				System.out.println(md5String);
				return md5String;
			}

			// 对密码进行判断匹配
			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				String encode = DigestUtils.md5DigestAsHex(rawPassword.toString().getBytes());
//				System.out.println("====");
//				System.out.println(encodedPassword);
//				System.out.println(encode);
				boolean res = encodedPassword.equals(encode);
//				System.out.println(res);
//				System.out.println("====");
				return res;
			}

		});
	}

	// @formatter:off
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests(authorizeRequests -> authorizeRequests.antMatchers("/css/**", "/index").permitAll()
//				.antMatchers("/user/**").hasRole("USER"))
//				.formLogin(formLogin -> formLogin.loginPage("/login").failureUrl("/login-error"));
		http.authorizeRequests().antMatchers("/", "index", "/login", "login-error", "/401", "/css/**", "/js/**")
				.permitAll().anyRequest().authenticated().and().formLogin().loginPage("/login")
				.failureUrl("/login-error").and().exceptionHandling().accessDeniedPage("/401");
		http.logout().logoutSuccessUrl("/");
	}
	// @formatter:on

}
