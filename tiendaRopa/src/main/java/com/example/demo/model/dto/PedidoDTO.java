/*
package com.example.demo.model.dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.example.demo.repository.entity.Pedido;
import com.example.demo.repository.entity.PedidoProducto;
import com.example.demo.repository.entity.Usuario;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.ToString;

@Data
public class PedidoDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String numeroFactura;
	private Float precio;
	private String estado;
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date fechaEmision;
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date fechaEntrega;
	@ToString.Exclude
	private List<UsuarioDTO> listaClientesDTO;
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
		
		
		return pedidoDTO;
		
	}
	
}
*/