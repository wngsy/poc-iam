package wn.demo.pociam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PocIamApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocIamApplication.class, args);
	}

}
