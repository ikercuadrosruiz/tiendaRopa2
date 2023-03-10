package com.example.demo.services;

import java.util.List;

import com.example.demo.model.dto.UsuarioDTO;

public interface UsuarioService {

	List<UsuarioDTO> findAll();

	UsuarioDTO findById(Long idUsuario);

	void deleteById(Long idUsuario);

	void save(UsuarioDTO usuarioDTO);

	List<UsuarioDTO> findAllTrabajadores();

}
