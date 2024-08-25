package com.makin.makinapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.makin.makinapi.proxy")
public class MakinapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MakinapiApplication.class, args);
	}

}
