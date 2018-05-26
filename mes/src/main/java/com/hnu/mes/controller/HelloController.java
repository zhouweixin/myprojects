package com.hnu.mes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloWorld
 * @author zhouweixin
 *
 */
@RestController
public class HelloController {
	@GetMapping(value = "/hello")
	public String hello(){
		return "Hello world!";
	}
}
