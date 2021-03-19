package org.casey.cloud.h.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.casey.cloud.h.client"})
public class CloudClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudClientApplication.class, args);
	}

}
