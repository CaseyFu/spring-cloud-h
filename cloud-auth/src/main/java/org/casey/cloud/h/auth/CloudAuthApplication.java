package org.casey.cloud.h.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.casey.cloud.h.auth"})
public class CloudAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudAuthApplication.class, args);
	}

}
