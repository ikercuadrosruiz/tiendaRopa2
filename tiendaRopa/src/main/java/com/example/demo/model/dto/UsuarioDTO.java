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
import com.example.demo.repository.entity.UsuarioRol;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.ToString;

@Data
public class UsuarioDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	/**/
	private Long id;
	
	@NotEmpty(message = "El campo no puede ser vacío")
	@Pattern(regexp = "^[A-HJNPW-Z0-9]{1}[0-9]{7}[A-HJNPW-Z0-9]{1}$", message = "El nif no tiene el formato correcto. Ej: 12345678A")
	private String nif;
	
	@NotEmpty(message = "El campo no puede ser vacío")
	@Pattern(regexp = "^[A-Za-z]+$", message = "El nombre solo puede contener letras")
	private String nombre;
	
	@NotEmpty(message = "El campo no puede ser vacío")
	@Pattern(regexp = "^[A-Za-z]+$", message = "El apellido solo puede contener letras")
	private String apellido1;
	
	@NotEmpty(message = "El campo no puede ser vacío")
	@Pattern(regexp = "^[A-Za-z]+$", message = "El apellido solo puede contener letras")
	private String apellido2;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaNacimiento;
	
	@NotEmpty(message = "El campo no puede ser vacío")
	private String direccion;
	
	@NotEmpty(message = "El campo no puede ser vacío")
	@Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ]+(?:[ -][A-Za-zÀ-ÖØ-öø-ÿ]+)*$", message = "La población no es válida")
	private String poblacion;
	
	@NotEmpty(message = "El campo no puede ser vacío")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,}$", message = "El correo electrónico ingresado no es válido")
	private String correo;
	
	@NotEmpty(message = "El campo no puede ser vacío")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z]).+$", message = "La contraseña debe tener al menos una minúscula y un número")
	private String password;
	
	@NotEmpty(message = "El campo no puede ser vacío")
	@Pattern(regexp = "^[0-9]{5}$", message = "El código postal debe contener 5 números")
	private String cp;
	
	@ToString.Exclude
	private List<UsuarioRolDTO> listUsuarioRolDTO;
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
		
		return usuario;
	}

	public UsuarioDTO() {
		super();
		this.listaPedidosDTO = new ArrayList<PedidoDTO>();
		this.listaCarritosDTO = new ArrayList<CarritoDTO>();
		this.listUsuarioRolDTO = new ArrayList<UsuarioRolDTO>();
	}
}
