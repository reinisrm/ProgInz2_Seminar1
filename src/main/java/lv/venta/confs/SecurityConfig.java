package lv.venta.confs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//norošināt lietotajus un to lomas
//userDetailsManager
	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withDefaultPasswordEncoder().username("karina.krinkele").password("123")
				.authorities("ADMIN").build());
		manager.createUser(User.withDefaultPasswordEncoder().username("janis.berzins").password("321")
				.authorities("USER").build());
		manager.createUser(User.withDefaultPasswordEncoder().username("liga.jauka").password("987")
				.authorities("USER", "ADMIN").build());

		return manager;
	}

//nodorošināt piekļuvi konkrētiem endpointien
//SecurityFilterChain
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
		.requestMatchers("/hello").permitAll()
		.requestMatchers("/msg").permitAll()
		.requestMatchers("/product").hasAnyAuthority("ADMIN")
		.requestMatchers("/productOne").hasAnyAuthority("ADMIN")
		.requestMatchers("/product/**").hasAnyAuthority("ADMIN", "USER")
		.requestMatchers("/allproducts").permitAll()
		.requestMatchers("/insert").hasAnyAuthority("ADMIN")
		.requestMatchers("/update/**").hasAnyAuthority("ADMIN")
		.requestMatchers("/delete/**").hasAnyAuthority("ADMIN")
		.requestMatchers("/filter/quantity/**").hasAnyAuthority("ADMIN")
		.requestMatchers("/error").permitAll()
		.and()
		.formLogin().permitAll()
		.and()
		.logout().permitAll();
		
		return http.build();
	}

}
