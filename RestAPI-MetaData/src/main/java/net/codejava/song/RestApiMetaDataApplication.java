package net.codejava.song;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class RestApiMetaDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiMetaDataApplication.class, args);
	}

}
