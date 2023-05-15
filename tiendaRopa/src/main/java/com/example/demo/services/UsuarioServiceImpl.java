package com.example.demo.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.configuration.EncriptaPassword;
import com.example.demo.model.dto.RolDTO;
import com.example.demo.model.dto.UsuarioDTO;
import com.example.demo.repository.dao.RolRepository;
import com.example.demo.repository.dao.UsuarioRepository;
import com.example.demo.repository.entity.Rol;
import com.example.demo.repository.entity.Usuario;

import jakarta.validation.Valid;

@Service
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

	private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private RolRepository rolRepository;

	@Override
	public List<UsuarioDTO> findAll() {

		log.info("UsuarioServiceImpl - FindAll: Encontramos todos los Usuario");

		List<Usuario> listaUsuarios = usuarioRepository.findAll();

		List<UsuarioDTO> listaUsuariosDTO = new ArrayList<UsuarioDTO>();
		for (Usuario u : listaUsuarios) {

			UsuarioDTO uDTO = UsuarioDTO.convertToDTO(u);

			// Añado el usuarioDTO a la lista de UsuariosDTO
			listaUsuariosDTO.add(uDTO);
		}

		return listaUsuariosDTO;
	}

	@Override
	public List<UsuarioDTO> findAllTrabajadores() {
		log.info("UsuarioServiceImpl - findAllTrabajadores: Encontramos todos los Trabajadores");

		List<Usuario> lt = usuarioRepository.findAllTrabajadores();

		List<UsuarioDTO> ltDTO = new ArrayList<UsuarioDTO>();
		for (Usuario u : lt) {
			// Obtengo sus roles
			Set<Rol> lr = rolRepository.findAllByIdCliente(u.getId());
			ArrayList<RolDTO> lrDTO = new ArrayList<RolDTO>();
			for (Rol r : lr) {
				lrDTO.add(RolDTO.convertToDTO(r));
			}

			UsuarioDTO uDTO = UsuarioDTO.convertToDTO(u);
			uDTO.setListaRolesDTO(lrDTO);
			// Añado el usuarioDTO a la lista de UsuariosDTO
			ltDTO.add(uDTO);
		}

		return ltDTO;
	}

	@Override
	public UsuarioDTO findById(Long idUsuario) {

		log.info("UsuarioServiceImpl - findById: Encontramos el Usuario por id");

		Optional<Usuario> opUsuario = usuarioRepository.findById(idUsuario);
		if (opUsuario.isPresent()) {
			// Obtengo sus roles
			Set<Rol> lr = rolRepository.findAllByIdCliente(opUsuario.get().getId());
			ArrayList<RolDTO> lrDTO = new ArrayList<RolDTO>();
			for (Rol r : lr) {
				lrDTO.add(RolDTO.convertToDTO(r));
			}

			UsuarioDTO usuarioDTO = UsuarioDTO.convertToDTO(opUsuario.get());
			usuarioDTO.setListaRolesDTO(lrDTO);

			return usuarioDTO;

		} else {
			return null;
		}
	}

	@Override
	public void deleteById(Long idUsuario) {

		log.info("UsuarioServiceImpl - deleteById: Borramos el Usuario " + idUsuario);

		usuarioRepository.deleteById(idUsuario);

	}

	@Override
	public void save(UsuarioDTO usuarioDTO) {

		log.info("UsuarioServiceImpl - save: Guardamos los datos del usuario " + usuarioDTO.getId());

		Usuario usuario = UsuarioDTO.convertToEntity(usuarioDTO);
		usuario.setPassword(EncriptaPassword.encriptarPassword(usuario.getPassword()));
		usuarioRepository.save(usuario);
	}

	@Override
	public UsuarioDTO findByEmailAndPassword(UsuarioDTO uDTO) {

		log.info("UsuarioServiceImpl - findByEmailAndPassword: Buscamos el usuario por email y contraseña");

		List<Usuario> lu = usuarioRepository.findByEmailAndPassword(uDTO.getCorreo(), uDTO.getPassword());

		if (lu.size() > 0) {
			return UsuarioDTO.convertToDTO(lu.get(0));
		} else {
			return null;
		}

	}
	
	@Override
	public UsuarioDTO findByCorreo(String correo) {
		log.info("UsuarioServiceImpl - findByCorreo: Buscamos el usuario por email");

		Usuario u = usuarioRepository.findByCorreo(correo);
		
		if (u!=null) {
			return UsuarioDTO.convertToDTO(u);
		}else {return null;}
		
	}

	// ??????????????????????????????????????????????????????????

	@Override // Username == Correo del usuario
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("UsuarioServiceImpl - loadUserByUsername: " + username);
		
		Usuario usuario = usuarioRepository.findByCorreo(username);
		
		if (usuario != null) {
			List<GrantedAuthority> listaPermisos = new ArrayList<GrantedAuthority>();
			List<Rol> listaRoles = new ArrayList<Rol>(usuario.getListaRoles());
			for (Rol rol : listaRoles) {
				listaPermisos.add(new SimpleGrantedAuthority(rol.getRol()));
			}
			
			return new User(usuario.getCorreo(), usuario.getPassword(), listaPermisos);
			
		} else {
			throw new UsernameNotFoundException(username);
		}
	}

	@Override
	public List<UsuarioDTO> findAllByTerm(String searchTerm) {
		
		log.info("UsuarioServiceImpl - findAllByTerm: Encontramos todos los Usuario");

		List<Usuario> listaUsuarios = usuarioRepository.findAllByTerm(searchTerm);

		List<UsuarioDTO> listaUsuariosDTO = new ArrayList<UsuarioDTO>();
		for (Usuario u : listaUsuarios) {

			UsuarioDTO uDTO = UsuarioDTO.convertToDTO(u);

			// Añado el usuarioDTO a la lista de UsuariosDTO
			listaUsuariosDTO.add(uDTO);
		}

		return listaUsuariosDTO;
	}

	@Override
	public List<UsuarioDTO> findAllTrabajadoresByTerm(String searchTerm) {
		log.info("UsuarioServiceImpl - findAllByTerm: Encontramos todos los Usuario");

		List<Usuario> lt = usuarioRepository.findAllTrabajadoresByTerm(searchTerm);

		List<UsuarioDTO> ltDTO = new ArrayList<UsuarioDTO>();
		for (Usuario u : lt) {
			// Obtengo sus roles
			Set<Rol> lr = rolRepository.findAllByIdCliente(u.getId());
			ArrayList<RolDTO> lrDTO = new ArrayList<RolDTO>();
			for (Rol r : lr) {
				lrDTO.add(RolDTO.convertToDTO(r));
			}

			UsuarioDTO uDTO = UsuarioDTO.convertToDTO(u);
			uDTO.setListaRolesDTO(lrDTO);
			// Añado el usuarioDTO a la lista de UsuariosDTO
			ltDTO.add(uDTO);
		}

		return ltDTO;
	}
	
}
