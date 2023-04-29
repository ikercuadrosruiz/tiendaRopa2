package com.example.demo.configuration;

import java.io.IOException;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	@Override 
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException { 
		Set roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities()); 
		if (roles.contains("ROLE_ADMINISTRADOR") || roles.contains("ROLE_TRABAJADOR")) { 
			response.sendRedirect("/fruttidivestiti/trabajadores");
		} else { response.sendRedirect("/fruttidivestiti/tienda"); } 
	}
	
	/*
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

		if (roles.contains("ADMINISTRADOR") || roles.contains("TRABAJADOR")) {
			response.sendRedirect("/trabajadores");
		} else if (roles.contains("ROLE_USUARIO")) {
			response.sendRedirect("/tienda");
		} else {
			throw new IllegalStateException("No se pudo determinar el rol del usuario.");
		}
	}
	*/

}
