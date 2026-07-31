package com.example.springSecurityApp;

import com.example.springSecurityApp.entities.User;
import com.example.springSecurityApp.services.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringSecurityAppApplicationTests {

	@Autowired
	private JwtService jwtService;

//	@Test
//	void contextLoads() {
//
//		User user = new User(4L, "shubham@gmail.com", "Shubham123");
//		String token = jwtService.generateToken(user);
//		System.out.println(token);
//
//		Long id = jwtService.getUserIdFromToken(token);
//		System.out.println(id);
//
//	}

}
