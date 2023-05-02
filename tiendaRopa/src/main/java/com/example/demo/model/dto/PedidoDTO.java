package com.example.demo.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.repository.entity.Pedido;
import com.example.demo.repository.entity.PedidoProducto;

import lombok.Data;
import lombok.ToString;

@Data
public class PedidoDTO implements Serializable {

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
	private UsuarioDTO usuarioDTO;
	@ToString.Exclude
	private ArrayList<PedidoProductoDTO> listaPedidoProductoDTO;

	// --------------------------------------
	// ConvertToDTO
	// Pasamos de una Pedido a PedidoDTO
	public static PedidoDTO convertToDTO(Pedido pedido) {

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

	// --------------------------------------
	// ConvertToEntity
	// Pasamos de un PedidoDTO a Pedido
	public static Pedido convertToEntity(PedidoDTO pedidoDTO) {

		Pedido pedido = new Pedido();

		pedido.setId(pedidoDTO.getId());
		pedido.setNumeroFactura(pedidoDTO.getNumeroFactura());
		pedido.setPrecio(pedidoDTO.getPrecio());
		pedido.setEstado(pedidoDTO.getEstado());
		pedido.setFechaEmision(pedidoDTO.getFechaEmision());
		pedido.setFechaEntrega(pedidoDTO.getFechaEntrega());
		pedido.setUsuario(UsuarioDTO.convertToEntity(pedidoDTO.getUsuarioDTO()));

		return pedido;

	}

	public PedidoDTO() {
		super();
		this.usuarioDTO = new UsuarioDTO();
		this.listaPedidoProductoDTO = new ArrayList<PedidoProductoDTO>();
	}
}