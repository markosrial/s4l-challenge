package com.markosrial.s4lchallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class S4LChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(S4LChallengeApplication.class, args);
	}

}
