package com.example.demo.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.example.demo.repository.entity.Categoria;
import com.example.demo.repository.entity.Imagen;
import com.example.demo.repository.entity.PedidoProducto;
import com.example.demo.repository.entity.Producto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.ToString;

@Data
public class ProductoDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotEmpty(message="El campo numero de Producto no puede estar vacío")
	private String numeroProducto;
	private String nombre;
	private int stock;
	private Float precio;
	private String talla;
	private String color;
	private String descripcion;
	@ToString.Exclude
	private Categoria categoria;
	@ToString.Exclude
	private List<PedidoProductoDTO> listaPedidoProductoDTO;
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
			
			return p;
		}
	
	// Constructores 
	public ProductoDTO() {
		super();
		this.listaPedidoProductoDTO = new ArrayList<PedidoProductoDTO>();
		this.categoria = new Categoria();
		this.listaImagenesDTO = new ArrayList<ImagenDTO>();
	}
	
	

}
