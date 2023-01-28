package tn.esprithub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class EsprithubApplication {

	public static void main(String[] args) {
		SpringApplication.run(EsprithubApplication.class, args);
	}

}
