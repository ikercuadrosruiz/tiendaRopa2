package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.UsuarioDTO;
import com.example.demo.repository.dao.UsuarioRepository;
import com.example.demo.repository.entity.Usuario;

import jakarta.validation.Valid;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public List<UsuarioDTO> findAll() {
		
		log.info("UsuarioServiceImpl - FindAll: Encontramos todos los Usuario");
		
		List<Usuario> listaUsuarios = usuarioRepository.findAll();
		
		List<UsuarioDTO> listaUsuariosDTO = new ArrayList<UsuarioDTO>();
		for (Usuario u : listaUsuarios) {
			listaUsuariosDTO.add(UsuarioDTO.convertToDTO(u));
		}
		
		return listaUsuariosDTO;
	}

	@Override
	public List<UsuarioDTO> findAllTrabajadores() {
		log.info("UsuarioServiceImpl - findAllTrabajadores: Encontramos todos los Trabajadores");
		
		List<Usuario> lt = usuarioRepository.findAllTrabajadores();
		
		List<UsuarioDTO> ltDTO = new ArrayList<UsuarioDTO>();
		for (Usuario u : lt) {
			ltDTO.add(UsuarioDTO.convertToDTO(u));
		}
		
		return ltDTO;
	}
	
	@Override
	public UsuarioDTO findById(Long idUsuario) {
		
		log.info("UsuarioServiceImpl - findById: Encontramos el Usuario por id");
		
		Optional<Usuario> opUsuario = usuarioRepository.findById(idUsuario);
		if (opUsuario.isPresent()) {
			UsuarioDTO usuarioDTO = UsuarioDTO.convertToDTO(opUsuario.get());
			return usuarioDTO;
		}else {
			return null;
		}
	}

	@Override
	public void deleteById(Long idUsuario) {
		
		log.info("UsuarioServiceImpl - deleteById: Borramos el Usuario " + idUsuario );
		
		usuarioRepository.deleteById(idUsuario);
		
	}

	@Override
	public void save(UsuarioDTO usuarioDTO) {
		
		log.info("UsuarioServiceImpl - save: Guardamos los datos del usuario " + usuarioDTO.getId() );
		
		Usuario usuario = UsuarioDTO.convertToEntity(usuarioDTO);
		usuarioRepository.save(usuario);
	}

	@Override
	public UsuarioDTO findByEmailAndPassword(UsuarioDTO uDTO) {
		
		log.info("UsuarioServiceImpl - findByEmailAndPassword: Buscamos el usuario por email y contraseña" );
		
		List<Usuario> lu = usuarioRepository.findByEmailAndPassword(uDTO.getCorreo(), uDTO.getPassword());
		
		if (lu.size() > 0) {
			return UsuarioDTO.convertToDTO(lu.get(0));
		}else {
			return null;
		}
		
	}
}
