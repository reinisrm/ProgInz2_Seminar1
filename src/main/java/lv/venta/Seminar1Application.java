package lv.venta;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import lv.venta.models.security.MyAuthority;
import lv.venta.models.security.MyUser;

import lv.venta.repo.IMyAuthorityRepo;
import lv.venta.repo.security.IMyUserRepo;

@SpringBootApplication
public class Seminar1Application {

	public static void main(String[] args) {
		SpringApplication.run(Seminar1Application.class, args);
	}
	
	@Bean
	public PasswordEncoder passwordEncoderSimple() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	
	
	@Bean
	public CommandLineRunner testDB(IMyUserRepo userRepo, IMyAuthorityRepo authorityRepo) {
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				
				MyUser user1 = new MyUser("Karina", "Krinkele", passwordEncoderSimple().encode("123"));
				userRepo.save(user1);
				
				MyUser user2 = new MyUser("Janis", "Berzins", passwordEncoderSimple().encode("321"));
				userRepo.save(user2);
				
				MyAuthority auth1 = new MyAuthority("ADMIN");
				MyAuthority auth2 = new MyAuthority("USER");
				
				auth1.addUser(user1); // Karina ka ADMIN
				auth2.addUser(user2); // Janis ka USER
				auth2.addUser(user1); //Karina ka USER
				authorityRepo.save(auth1);
				authorityRepo.save(auth2);
				
				user1.addAuthority(auth1);
				user1.addAuthority(auth2);
				user2.addAuthority(auth2);
				userRepo.save(user1);
				userRepo.save(user2);
				
				
				
			}
		};
	}
}
