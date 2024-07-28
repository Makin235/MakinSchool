package com.makin.makinschool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
public class MakinSchoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(MakinSchoolApplication.class, args);
	}

}
