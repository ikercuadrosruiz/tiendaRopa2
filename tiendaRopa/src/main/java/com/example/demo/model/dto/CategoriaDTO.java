package com.example.demo.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.example.demo.repository.entity.Categoria;
import com.example.demo.repository.entity.Producto;

import lombok.Data;

@Data
public class CategoriaDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nombre;
	
	// Convert to DTO
	public static CategoriaDTO convertToDTO(Categoria c) {
		
		CategoriaDTO cDTO = new CategoriaDTO();
		
		cDTO.setId(c.getId());
		cDTO.setNombre(c.getNombre());
		
		return cDTO;
	}
	
	//Convert to Entity
	public static Categoria convertToEntity(CategoriaDTO cDTO) {
		
		Categoria c = new Categoria();
		
		c.setId(cDTO.getId());
		c.setNombre(cDTO.getNombre());
		
		return c;
	}
	
	// Constructor ------------------------------
	public CategoriaDTO() {
		super();
	}
	
}
