package com.example.demo.model.dto;

import java.io.Serializable;

import com.example.demo.repository.entity.Carrito;
import com.example.demo.repository.entity.CarritoProducto;
import com.example.demo.repository.entity.Producto;

import lombok.Data;
import lombok.ToString;

@Data
public class CarritoProductoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private int cantidad;
	@ToString.Exclude
	private CarritoDTO carritoDTO;
	@ToString.Exclude
	private ProductoDTO productoDTO;
	
	// Convert to DTO
	public static CarritoProductoDTO convertToDTO(CarritoProducto cp) {
		
		CarritoProductoDTO cpDTO = new CarritoProductoDTO();
		
		cpDTO.setId(cp.getId());
		cpDTO.setCantidad(cp.getCantidad());
		cpDTO.setCarritoDTO(CarritoDTO.convertToDTO(cp.getCarrito()));
		cpDTO.setProductoDTO(ProductoDTO.convertToDTO(cp.getProducto()));
		
		return cpDTO;
		
	}
	
	
	// Convert to String
	public static CarritoProducto convertToEntity(CarritoProductoDTO cpDTO) {
		
		CarritoProducto cp = new CarritoProducto();
		
		cp.setId(cpDTO.getId());
		cp.setCantidad(cpDTO.getCantidad());
		cp.setCarrito(CarritoDTO.convertToEntity(cpDTO.getCarritoDTO()));
		cp.setProducto(ProductoDTO.convertToEntity(cpDTO.getProductoDTO()));
		
		return cp;
		
	}
	
	// Constructor
	public CarritoProductoDTO() {
		super();
		this.carritoDTO = new CarritoDTO();
		this.productoDTO = new ProductoDTO();
	}
	
	
}
