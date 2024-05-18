package com.example.IssueTrackingSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class IssueTrackingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(IssueTrackingSystemApplication.class, args);
	}

}
