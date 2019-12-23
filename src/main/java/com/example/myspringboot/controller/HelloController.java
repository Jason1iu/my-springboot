package com.example.myspringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/** 
* @author LiuJie 
* @date 2019年12月23日 下午6:26:20 
* 
*/
@Controller
public class HelloController {
	
	@GetMapping("/hello")
	public String hello(String name) {
		return "hello, " + name;
	}
}
