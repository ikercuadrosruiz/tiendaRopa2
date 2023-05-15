package com.example.demo.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.repository.entity.Categoria;
import com.example.demo.repository.entity.Imagen;
import com.example.demo.repository.entity.Producto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.ToString;

@Data
public class ProductoDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotEmpty(message="El Número de Producto no puede estar vacío")
	private String numeroProducto;
	
	@NotEmpty(message = "El campo no puede ser vacío")
	@Pattern(regexp = "^[A-Za-z\\s]+$", message = "El nombre sólo puede contener letras")
	private String nombre;
	
	@NotNull(message = "El campo no puede ser nulo")
	@Min(value = 0, message = "El stock del producto debe ser igual o mayor que 0")
	private Integer stock;
	
	@NotNull(message = "El campo no puede ser vacío")
	@DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor que 0")
	private Float precio;
	
	@NotEmpty(message = "El campo no puede ser vacío")
	@Pattern(regexp = "^(XS|S|M|L|XL|XXL|XXXL)$", message = "Selecciona una talla existente: XS|S|M|L|XL|XXL|XXXL")
	private String talla;
	
	private String color;
	private String descripcion;
	
	@ToString.Exclude
	private Categoria categoria;
	
	@ToString.Exclude
	private List<ImagenDTO> listaImagenesDTO;
	
	// Convert to DTO
	public static ProductoDTO convertToDTO(Producto p) {
		
		ProductoDTO pDTO = new ProductoDTO();
		
		pDTO.setId(p.getId());
		pDTO.setNumeroProducto(p.getNumeroProducto());
		pDTO.setNombre(p.getNombre());
		pDTO.setStock(p.getStock());
		pDTO.setPrecio(p.getPrecio());
		pDTO.setTalla(p.getTalla());
		pDTO.setColor(p.getColor());
		pDTO.setDescripcion(p.getDescripcion());
		pDTO.getCategoria().setNombre(p.getCategoria().getNombre());
		pDTO.getCategoria().setId(p.getCategoria().getId());
		
		List<Imagen> listaImagenes = new ArrayList<Imagen>(p.getListaImagenes());
		for (Imagen i : listaImagenes) {
			ImagenDTO iDTO = new ImagenDTO();
			
			iDTO.setId(i.getId());
			iDTO.setNombre(i.getNombre());
			iDTO.setUrl(i.getUrl());
			
			pDTO.getListaImagenesDTO().add(iDTO);
		}
		
		return pDTO;
	}
	
	// Convert to Entity
		public static Producto convertToEntity(ProductoDTO pDTO) {
			
			Producto p = new Producto();
			
			p.setId(pDTO.getId());
			p.setNumeroProducto(pDTO.getNumeroProducto());
			p.setNombre(pDTO.getNombre());
			p.setStock(pDTO.getStock());
			p.setPrecio(pDTO.getPrecio());
			p.setTalla(pDTO.getTalla());
			p.setColor(pDTO.getColor());
			p.setDescripcion(pDTO.getDescripcion());
			p.getCategoria().setId(pDTO.getCategoria().getId());
			
			List<ImagenDTO> listaImagenesDTO = pDTO.getListaImagenesDTO();
			for (ImagenDTO iDTO : listaImagenesDTO) {
				Imagen i = new Imagen();
				
				i.setId(iDTO.getId());
				i.setNombre(iDTO.getNombre());
				i.setUrl(iDTO.getUrl());
				
				p.getListaImagenes().add(i);
			}
			
			return p;
		}
	
	// Constructores 
	public ProductoDTO() {
		super();
		this.categoria = new Categoria();
		this.listaImagenesDTO = new ArrayList<ImagenDTO>();
	}
	
	

}
