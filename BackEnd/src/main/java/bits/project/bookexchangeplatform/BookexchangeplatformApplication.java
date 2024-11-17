package bits.project.bookexchangeplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication(scanBasePackages = {"bits.project.bookexchangeplatform"})
public class BookexchangeplatformApplication {
	public static void main(String[] args) {
		SpringApplication.run(BookexchangeplatformApplication.class, args);
	}
}
