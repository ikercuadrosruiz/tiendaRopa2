package com.example.demo.model.dto;

import java.io.Serializable;

import com.example.demo.repository.entity.CarritoProducto;
import com.example.demo.repository.entity.UsuarioRol;

import lombok.Data;
import lombok.ToString;

@Data
public class UsuarioRolDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	@ToString.Exclude
	private UsuarioDTO usuarioDTO;
	@ToString.Exclude
	private RolDTO rolDTO;

	// Convert to DTO
	public static UsuarioRolDTO convertToDTO(UsuarioRol ur) {

		UsuarioRolDTO urDTO = new UsuarioRolDTO();

		urDTO.setId(ur.getId());
		urDTO.setUsuarioDTO(UsuarioDTO.convertToDTO(ur.getUsuario()));
		urDTO.setRolDTO(RolDTO.convertToDTO(ur.getRol()));

		return urDTO;

	}

	// Convert to String
	public static UsuarioRol convertToEntity(UsuarioRolDTO urDTO) {

		UsuarioRol ur = new UsuarioRol();

		ur.setId(urDTO.getId());
		ur.setUsuario(UsuarioDTO.convertToEntity(urDTO.getUsuarioDTO()));
		ur.setRol(RolDTO.convertToEntity(urDTO.getRolDTO()));

		return ur;

	}

	// Constructor
	public UsuarioRolDTO() {
		super();
		this.usuarioDTO = new UsuarioDTO();
		this.rolDTO = new RolDTO();
	}
}
