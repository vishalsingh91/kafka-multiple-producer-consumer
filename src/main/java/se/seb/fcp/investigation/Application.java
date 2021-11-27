package se.seb.fcp.investigation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "se.seb.fcp.investigation.*")
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
