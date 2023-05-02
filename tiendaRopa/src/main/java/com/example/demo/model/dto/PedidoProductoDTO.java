package com.example.demo.model.dto;

import java.io.Serializable;

import com.example.demo.repository.entity.Pedido;
import com.example.demo.repository.entity.PedidoProducto;
import com.example.demo.repository.entity.Producto;

import lombok.Data;
import lombok.ToString;

@Data
public class PedidoProductoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	 
	private Long id;
	private int cantidad;
	@ToString.Exclude
	private ProductoDTO productoDTO;
	@ToString.Exclude
	private PedidoDTO pedidoDTO;
	
	// Convert to DTO
	public static PedidoProductoDTO convertToDTO(PedidoProducto pp) {
		PedidoProductoDTO ppDTO = new PedidoProductoDTO();
		
		ppDTO.setId(pp.getId());
		ppDTO.setCantidad(pp.getCantidad());
		ppDTO.setPedidoDTO(PedidoDTO.convertToDTO(pp.getPedido()));
		ppDTO.setProductoDTO(ProductoDTO.convertToDTO(pp.getProducto()));
		
		return ppDTO;
	}
	
	// Convert to Entity
	public static PedidoProducto convertToEntity(PedidoProductoDTO ppDTO) {
		
		PedidoProducto pp = new PedidoProducto();
		
		pp.setId(ppDTO.getId());
		pp.setCantidad(ppDTO.getCantidad());
		pp.setPedido(PedidoDTO.convertToEntity(ppDTO.getPedidoDTO()));
		pp.setProducto(ProductoDTO.convertToEntity(ppDTO.getProductoDTO()));
		
		return pp;
	}
	
	// Constructores -------------
	public PedidoProductoDTO() {
		super();
		this.productoDTO = new ProductoDTO();
		this.pedidoDTO = new PedidoDTO();
	}

	
}
