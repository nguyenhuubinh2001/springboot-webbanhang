package poly.edu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import poly.edu.entity.User;
import poly.edu.repository.UserRepository;

@SpringBootApplication
public class AsmJava6Application  {

	public static void main(String[] args) {
		SpringApplication.run(AsmJava6Application.class, args);
	}
}
