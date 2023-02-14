package com.example.demo.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.example.demo.repository.entity.Carrito;
// import com.example.demo.repository.entity.CarritoProducto;
import com.example.demo.repository.entity.Usuario;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.ToString;

@Data
public class CarritoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	/**/

	private Long id;
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private Date fechageneracion;
	private String estado;
	@ToString.Exclude
	private UsuarioDTO usuarioDTO;
	/*
	 * @ToString.Exclude private List<CarritoProducto> listaCarritoProducto;
	 */

	// --------------------------------------
	// ConvertToDTO
	// Pasamos de un Carrito a CarritoDTO
	public static CarritoDTO convertToDTO(Carrito carrito) {

		CarritoDTO carritoDTO = new CarritoDTO();

		carritoDTO.setId(carrito.getId());
		carritoDTO.setFechageneracion(carrito.getFechageneracion());
		carritoDTO.setEstado(carrito.getEstado());

		return carritoDTO;
	}

	// ConvertToEntity
	// Pasamos de UsuarioDTO a Usuario
	public static Carrito convertToEntity(CarritoDTO carritoDTO) {

		Carrito carrito = new Carrito();

		carrito.setId(carritoDTO.getId());
		carrito.setFechageneracion(carritoDTO.getFechageneracion());
		carrito.setEstado(carritoDTO.getEstado());

		return carrito;
	}

}
