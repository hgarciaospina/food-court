package com.pragma.powerup.usermicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class UserMicroserviceApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(UserMicroserviceApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		int i = 0;
		while (i < 5) {
			String password = "1234";
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(password);

			System.out.println(hashedPassword);
			i++;
		}
	}
}