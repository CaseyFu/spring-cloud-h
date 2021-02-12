package org.casey.cloud.h.oauth2.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.casey.oauth2.api"})
public class CloudOauth2ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudOauth2ApiApplication.class, args);
	}

}
