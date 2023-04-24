package com.example.demo.model.dto;

import java.io.Serializable;
import java.util.List;

import com.example.demo.repository.entity.Carrito;
import com.example.demo.repository.entity.Categoria;
import com.example.demo.repository.entity.Rol;

import lombok.Data;
import lombok.ToString;

@Data
public class RolDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String rol;

	// --------------------------------------
	// ConvertToDTO
	// Pasamos de un Carrito a CarritoDTO
	public static RolDTO convertToDTO(Rol rol) {

		RolDTO rolDTO = new RolDTO();

		rolDTO.setId(rol.getId());
		rolDTO.setRol(rol.getRol());

		return rolDTO;
	}

	// ConvertToEntity
	public static Rol convertToEntity(RolDTO rolDTO) {

		Rol rol = new Rol();

		rol.setId(rolDTO.getId());
		rol.setRol(rolDTO.getRol());

		return rol;
	}
	
	// Constructor --------------------
	public RolDTO() {
		super();
	}

}
