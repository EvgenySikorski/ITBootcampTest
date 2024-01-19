package by.itacademy.itbootcamp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories
@EnableTransactionManagement
@SpringBootApplication
public class ItbootcampApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItbootcampApplication.class, args);
	}

}
