package org.casey.cloud.h.account.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.casey.cloud.h.account.service"})
public class CloudAccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudAccountServiceApplication.class, args);
	}

}
