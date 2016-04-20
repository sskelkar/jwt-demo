package com.maxxton.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.maxxton.jwt.service.JwtUtil;

@SpringBootApplication
public class JwtDemoApplication {


	public static void main(String[] args) {
		SpringApplication.run(JwtDemoApplication.class, args);
		
		String token = JwtUtil.generateToken(21L);
		System.out.println("token: "+ token);
		System.out.println("is valid: "+ JwtUtil.isTokenValid(token));
		System.out.println("emp id: "+JwtUtil.getEmployeeId(token));
	}
}
