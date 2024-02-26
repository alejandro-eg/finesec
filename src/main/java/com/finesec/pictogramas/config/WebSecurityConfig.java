package com.finesec.pictogramas.config;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.sql.DataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import com.finesec.pictogramas.model.Roles;
import com.finesec.pictogramas.model.Usuarios;
import com.finesec.pictogramas.repository.IUsuariosRepository;
import jakarta.persistence.Convert;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	
	@Autowired
	private IUsuariosRepository repo;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private DataSource dataSource; // Inyecta el DataSource

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/login", "/index","/olvide-contrasenia", "/cambiar-contrasenia").permitAll()
				.anyRequest().authenticated()
			)
			.formLogin((form) -> form
				.loginPage("/login")
				.permitAll()
				.defaultSuccessUrl("/", true)
			)
			.logout((logout) -> logout.permitAll());

		return http.build();
	}
	
	
	@Bean
	public UserDetailsService userDetailsService() {
		return userLogin -> {
			Usuarios usuario = repo.findByEmail(userLogin);

			if (usuario != null) {
				List<GrantedAuthority> authorities = new ArrayList<>();

				authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
	            
				System.out.println("Usuario recuperado de la base de datos2: " + usuario.getEmail());
				System.out.println("Contraseña recuperada de la base de datos2: " + usuario.getPassword());
				System.out.println("Usuario con sesion activa: " + usuario.getNombres());
				System.out.println("Usuario REESTRICCION ROL: " + usuario.getRestriccionRol());
						
				Roles rol = usuario.getRol();
				/*
				if (rol != null) {
				    if ("Administrador".equals(rol.getNombre())) {
				        System.out.println("Es admin");
				        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
				    }

				    if ("Profesor".equals(rol.getNombre())) {
				        System.out.println("Es profesor");
				        authorities.add(new SimpleGrantedAuthority("ROLE_PROFESOR"));
				    }		    
				}*/

			
				return User.builder()
						.username(usuario.getEmail())
						.password(passwordEncoder().encode(usuario.getPassword()))
						.authorities(authorities)
						.build();
			} else {
				System.out.println("Usuario");
				throw new UsernameNotFoundException("Usuario no encontrado: " + userLogin);
			}
		};
	}
	
	
}