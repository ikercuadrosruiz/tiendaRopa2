package com.example.demo.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.example.demo.repository.entity.Imagen;
import com.example.demo.repository.entity.Producto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.ToString;

@Data
public class ImagenDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nombre;
	private String url;
	@ToString.Exclude
	private List<ProductoDTO> listaProductosDTO;

	// Convert to DTO
	public static ImagenDTO convertToDTO(Imagen i) {

		ImagenDTO iDTO = new ImagenDTO();

		iDTO.setId(i.getId());
		iDTO.setNombre(i.getNombre());
		iDTO.setUrl(i.getUrl());

		return iDTO;
	}

	// Convert To Entity
	public static Imagen convertToEntity(ImagenDTO iDTO) {

		Imagen i = new Imagen();

		i.setId(iDTO.getId());
		i.setNombre(iDTO.getNombre());
		i.setUrl(iDTO.getUrl());

		return i;
	}

	// Constructor
	public ImagenDTO() {
		super();
		this.listaProductosDTO = new ArrayList<ProductoDTO>();
	}

}
