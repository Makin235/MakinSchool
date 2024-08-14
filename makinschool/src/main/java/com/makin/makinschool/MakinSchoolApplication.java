package com.makin.makinschool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.makin.makinschool.repository")
@EntityScan("com.makin.makinschool.model")
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class MakinSchoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(MakinSchoolApplication.class, args);
	}

}
