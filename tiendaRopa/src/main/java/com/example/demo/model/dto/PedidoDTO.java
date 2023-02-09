package com.example.demo.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.repository.entity.Pedido;
import lombok.Data;
import lombok.ToString;

@Data
public class PedidoDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String numeroFactura;
	private Float precio;
	private String estado;
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private Date fechaEmision;
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private Date fechaEntrega;
	@ToString.Exclude
	private UsuarioDTO usuarioDTO;
	// @ToString.Exclude
	// private Set<PedidoProducto> listaPedidoProductoDTO;
	
	// --------------------------------------
	// ConvertToDTO
	// Pasamos de una Pedido a PedidoDTO
	public static PedidoDTO convertToDTO(Pedido pedido){
		
		PedidoDTO pedidoDTO = new PedidoDTO();
		
		pedidoDTO.setId(pedido.getId());
		pedidoDTO.setNumeroFactura(pedido.getNumeroFactura());
		pedidoDTO.setPrecio(pedido.getPrecio());
		pedidoDTO.setEstado(pedido.getEstado());
		pedidoDTO.setFechaEmision(pedido.getFechaEmision());
		pedidoDTO.setFechaEntrega(pedido.getFechaEntrega());
		pedidoDTO.setUsuarioDTO(UsuarioDTO.convertToDTO(pedido.getUsuario()));
		
		
		return pedidoDTO;
		
	}
	
}