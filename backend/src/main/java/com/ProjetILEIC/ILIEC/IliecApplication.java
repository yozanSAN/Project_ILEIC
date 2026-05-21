package com.ProjetILEIC.ILIEC;

import com.ProjetILEIC.ILIEC.entity.User;
import com.ProjetILEIC.ILIEC.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class IliecApplication {
	public static void main(String[] args) {
		SpringApplication.run(IliecApplication.class, args);
	}
}