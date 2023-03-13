package com.example.demo.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.example.demo.repository.entity.Carrito;
// import com.example.demo.repository.entity.Pedido;
import com.example.demo.repository.entity.Usuario;

import lombok.Data;
import lombok.ToString;

@Data
public class UsuarioDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	/**/
	private Long id;
	private String nif;
	private String nombre;
	private String apellido1;
	private String apellido2;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaNacimiento;
	private String direccion;
	private String poblacion;
	private String correo;
	private String password;
	private String cp;
	private int esCliente;
	private int esTrabajador;
	private int esAdministrador;
	@ToString.Exclude
	private List<PedidoDTO> listaPedidosDTO;
	@ToString.Exclude
	private List<CarritoDTO> listaCarritosDTO;
	
	/*
	@ToString.Exclude
	private PedidoDTO pedidoDTO;
	*/
	
	// --------------------------------------
	// ConvertToDTO
	//Pasamos de una Usuario a UsuarioDTO
	public static UsuarioDTO convertToDTO(Usuario usuario) {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		
		usuarioDTO.setId(usuario.getId());
		usuarioDTO.setNif(usuario.getNif());
		usuarioDTO.setNombre(usuario.getNombre());
		usuarioDTO.setApellido1(usuario.getApellido1());
		usuarioDTO.setApellido2(usuario.getApellido2());
		usuarioDTO.setFechaNacimiento(usuario.getFechaNacimiento());
		usuarioDTO.setDireccion(usuario.getDireccion());
		usuarioDTO.setPoblacion(usuario.getPoblacion());
		usuarioDTO.setCorreo(usuario.getCorreo());
		usuarioDTO.setPassword(usuario.getPassword());
		usuarioDTO.setCp(usuario.getCp());
		usuarioDTO.setEsCliente(usuario.getEsCliente());
		usuarioDTO.setEsTrabajador(usuario.getEsTrabajador());
		usuarioDTO.setEsAdministrador(usuario.getEsAdministrador());
		
		return usuarioDTO;
	}
	
	// ConvertToEntity
	// Pasamos de UsuarioDTO a Usuario
	public static Usuario convertToEntity(UsuarioDTO usuarioDTO) {
		
		Usuario usuario = new Usuario();
		
		usuario.setId(usuarioDTO.getId());
		usuario.setNif(usuarioDTO.getNif());
		usuario.setNombre(usuarioDTO.getNombre());
		usuario.setApellido1(usuarioDTO.getApellido1());
		usuario.setApellido2(usuarioDTO.getApellido2());
		usuario.setFechaNacimiento(usuarioDTO.getFechaNacimiento());
		usuario.setDireccion(usuarioDTO.getDireccion());
		usuario.setPoblacion(usuarioDTO.getPoblacion());
		usuario.setCorreo(usuarioDTO.getCorreo());
		usuario.setPassword(usuarioDTO.getPassword());
		usuario.setCp(usuarioDTO.getCp());
		usuario.setEsCliente(usuarioDTO.getEsCliente());
		usuario.setEsTrabajador(usuarioDTO.getEsTrabajador());
		usuario.setEsAdministrador(usuarioDTO.getEsAdministrador());
		
		return usuario;
	}

	public UsuarioDTO() {
		super();
		this.listaPedidosDTO = new ArrayList<PedidoDTO>();
		this.listaCarritosDTO = new ArrayList<CarritoDTO>();
	}
}
