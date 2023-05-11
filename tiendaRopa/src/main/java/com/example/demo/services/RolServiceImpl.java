package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.RolDTO;
import com.example.demo.model.dto.UsuarioDTO;
import com.example.demo.repository.dao.RolRepository;
import com.example.demo.repository.entity.Rol;
import com.example.demo.repository.entity.Usuario;

@Service
public class RolServiceImpl implements RolService {

	private static final Logger log = LoggerFactory.getLogger(RolServiceImpl.class);
	
	@Autowired
	private RolRepository rolRepository;
	
	@Override
	public List<RolDTO> findAll() {
		log.info("RolServiceImpl - FindAll: Encontramos todos los Roles");

		List<Rol> listaRol = rolRepository.findAll();

		List<RolDTO> listaRolDTO = new ArrayList<RolDTO>();
		for (Rol r : listaRol) {

			RolDTO rDTO = RolDTO.convertToDTO(r);

			// Añado el usuarioDTO a la lista de UsuariosDTO
			listaRolDTO.add(rDTO);
		}

		return listaRolDTO;
	}

	@Override
	public RolDTO findByRol(String r) {
		return RolDTO.convertToDTO(rolRepository.findByRol(r));
	}

}
