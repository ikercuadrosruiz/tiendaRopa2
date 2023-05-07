package com.example.demo.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.configuration.CustomAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests()
				.requestMatchers("/", "/registro", "/registro/**", "/usuarios/save", "/trabajadores/usuarios/save" ,
						"/index", "/tienda", "/tienda/1", "/tienda/catergoria/{idCategoria}", "/tienda/2", "/tienda/producto/{idProducto}",
						"/css/**", "/images/**", "/js/**").permitAll()
				.requestMatchers("/trabajadores" ,"/trabajadores/**").hasRole("TRABAJADOR")
				.requestMatchers("/trabajadores/trabajadores", "/trabajadores/trabajadores/**").hasRole("ADMINISTRADOR")
				.anyRequest().authenticated()
			.and()
				.formLogin()
				.loginPage("/login")
				//.defaultSuccessUrl("/tienda")
				.successHandler(customAuthenticationSuccessHandler)
				.failureUrl("/login?error")
				.permitAll()
			.and()
				.logout()
				.permitAll()
				.logoutSuccessUrl("/login?logout")
			.and()
				.exceptionHandling().accessDeniedPage("/errors/403");
		
		return http.build();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user1 =
			User.withDefaultPasswordEncoder()
				.username("user")
				.password("password")
				.roles("CLIENTE")
				.build();
		UserDetails user2 =
			User.withDefaultPasswordEncoder()
				.username("admin")
				.password("password")
				.roles("TRABAJADOR","ADMINISTRADOR")
				.build();
		return new InMemoryUserDetailsManager(user1, user2);
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder build) throws Exception{
		build.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
}
