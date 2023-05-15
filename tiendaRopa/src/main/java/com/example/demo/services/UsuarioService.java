package com.example.demo.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.demo.model.dto.UsuarioDTO;

import jakarta.validation.Valid;

public interface UsuarioService {

	List<UsuarioDTO> findAll();

	UsuarioDTO findById(Long idUsuario);

	void deleteById(Long idUsuario);

	void save(UsuarioDTO usuarioDTO);

	List<UsuarioDTO> findAllTrabajadores();

	UsuarioDTO findByEmailAndPassword(@Valid UsuarioDTO uDTO);

	UsuarioDTO findByCorreo(String correo);

	List<UsuarioDTO> findAllByTerm(String searchTerm);

	List<UsuarioDTO> findAllTrabajadoresByTerm(String searchTerm);

}
