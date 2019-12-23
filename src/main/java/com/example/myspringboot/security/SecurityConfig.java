package com.example.myspringboot.security;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
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

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 校验用户
		auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordEncoder() {
			// 对密码进行加密
			@Override
			public String encode(CharSequence rawPassword) {
				String md5String = DigestUtils.md5DigestAsHex(rawPassword.toString().getBytes());
				return md5String;
			}

			// 对密码进行判断匹配
			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				String encode = DigestUtils.md5DigestAsHex(rawPassword.toString().getBytes());
				boolean res = encodedPassword.equals(encode);
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
		http.csrf().disable();
		http.requestMatchers()
			.antMatchers("/oauth/**", "/login", "/login-error")
			.and()
			.authorizeRequests()
			.antMatchers("/oauth/**").authenticated()
			.and()
			.formLogin().loginPage("/login").failureUrl("/login-error");
			
	}
	// @formatter:on

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new PasswordEncoder() {

			@Override
			public String encode(CharSequence rawPassword) {
				return rawPassword.toString();
			}

			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				return Objects.equals(rawPassword.toString(), encodedPassword);
			}
			
		};
	}
	
}
