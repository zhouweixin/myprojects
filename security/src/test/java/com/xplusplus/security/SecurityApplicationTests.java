package com.xplusplus.security;

import java.util.regex.Pattern;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void test1() {
		boolean b = Pattern.matches("^[A-Za-z]{2}$", "as");
		System.out.println(b);
	}

}
