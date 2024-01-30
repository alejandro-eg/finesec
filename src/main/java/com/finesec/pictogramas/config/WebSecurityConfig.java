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
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import com.finesec.pictogramas.model.Usuarios;
import com.finesec.pictogramas.repository.IUsuariosRepository;


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
				.requestMatchers("/", "/index").permitAll()
				.anyRequest().authenticated()
			)
			.formLogin((form) -> form
				.loginPage("/login")
				.permitAll()
				.defaultSuccessUrl("/principal", true)
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
                // Puedes agregar roles o permisos si los tienes en tu modelo
                System.out.println("Usuario recuperado de la base de datos: " + usuario.getEmail());
                System.out.println("Contraseña recuperada de la base de datos: " + usuario.getPassword());


                return User.builder()
                        .username(usuario.getEmail())
                        .password(passwordEncoder().encode(usuario.getPassword())) // Codifica la contraseña
                        //.password(usuario.getPassword())
                        .authorities(authorities)
                        .build();
            } else {
                throw new UsernameNotFoundException("Usuario no encontrado: " + userLogin);
            }
        };
    }
	
    
    
}