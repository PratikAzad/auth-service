package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.config.AuthServiceApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AuthServiceApplication.class})
public class AuthServiceApplicationTests {

	@Test
	public void contextLoads() {
		
	}
}
