package com.makin.makinschooladmin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer
@SpringBootApplication
public class MakinschooladminApplication {

	public static void main(String[] args) {
		SpringApplication.run(MakinschooladminApplication.class, args);
	}

}
