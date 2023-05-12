package com.example.demo.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.dto.RolDTO;
import com.example.demo.model.dto.UsuarioDTO;
import com.example.demo.services.RolService;
import com.example.demo.services.UsuarioService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Controller
public class UsuarioController {

	private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private RolService rs;

	@GetMapping("/trabajadores/usuarios")
	public ModelAndView findAll() {

		log.info("UsuarioController - FindAll: Encontramos todos los clientes");

		List<UsuarioDTO> listaUsuariosDTO = usuarioService.findAll();

		ModelAndView mav = new ModelAndView("trabajadores/gestion/gestionUsuarios");
		mav.addObject("listaUsuariosDTO", listaUsuariosDTO);

		return mav;
	}

	@GetMapping("/trabajadores/usuarios/delete/{idUsuario}")
	public ModelAndView delete(@PathVariable Long idUsuario) {

		log.info("UsuarioController - delete: Borramos el usuario " + idUsuario);

		usuarioService.deleteById(idUsuario);

		ModelAndView mav = new ModelAndView("redirect:/trabajadores/usuarios");
		return mav;

	}

	@GetMapping("/trabajadores/usuarios/update/{idUsuario}")
	public ModelAndView update(@PathVariable Long idUsuario) {

		log.info("UsuarioController - update: Actualizamos el usuario");

		UsuarioDTO usuarioDTO = usuarioService.findById(idUsuario);
		List<RolDTO> lrDTO = rs.findAll();

		ModelAndView mav = new ModelAndView("trabajadores/form/usuariosForm");
		mav.addObject("usuarioDTO", usuarioDTO);
		mav.addObject("listaRolesDTO", lrDTO);
		mav.addObject("mod", true);
		mav.addObject("isFirstIteration", true);

		return mav;
	}

	@PostMapping("/trabajadores/usuarios/save")
	public ModelAndView save(@Valid @ModelAttribute("usuarioDTO") UsuarioDTO usuarioDTO, BindingResult result, @RequestParam(value="roles") String[] roles, @RequestParam(value = "password", required = false) String pswd) {

		log.info("UsuarioController - save: Guardamos el usuario");
		
		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView("trabajadores/form/usuariosForm");
			List<RolDTO> lrDTO = rs.findAll();
			mav.addObject("mod", true);
			mav.addObject("listaRolesDTO", lrDTO);
			mav.addObject("isFirstIteration", true);
			return mav;
		}else {
			
			// Comprobar formulario
			String nif = usuarioDTO.getNif();
			String nombre = usuarioDTO.getNombre();
			String apellido1 = usuarioDTO.getApellido1();
			String apellido2 = usuarioDTO.getApellido2();
			Date fechaNacimiento = usuarioDTO.getFechaNacimiento();
			String direccion = usuarioDTO.getDireccion();
			String poblacion = usuarioDTO.getPoblacion();
			String correo = usuarioDTO.getCorreo();
			String password = pswd;
			String cp = usuarioDTO.getCp();
			ArrayList<RolDTO> lrDTO = new ArrayList<RolDTO>();

			usuarioDTO = usuarioService.findById(usuarioDTO.getId());
			usuarioDTO.setNif(nif);
			usuarioDTO.setNombre(nombre);
			usuarioDTO.setApellido1(apellido1);
			usuarioDTO.setNombre(apellido2);
			usuarioDTO.setFechaNacimiento(fechaNacimiento);
			usuarioDTO.setDireccion(direccion);
			usuarioDTO.setPoblacion(poblacion);
			usuarioDTO.setCorreo(correo);
			usuarioDTO.setPassword(password);
			usuarioDTO.setCp(cp);
			
			for (String r : roles) {
				RolDTO rDTO = rs.findByRol(r);
				lrDTO.add(rDTO);
			}
			
			usuarioDTO.setListaRolesDTO(lrDTO);

			usuarioService.save(usuarioDTO);

			ModelAndView mav = new ModelAndView("redirect:/trabajadores/usuarios");
			return mav;
			
		}
		
	}

	@PostMapping("/usuarios/save")
	public ModelAndView saveDesdeCliente(@Valid @ModelAttribute("usuarioDTO") UsuarioDTO usuarioDTO, BindingResult result,  @RequestParam(value = "password", required = false) String pswd) {

		log.info("UsuarioController - saveDesdeCliente: Guardamos el usuario");
		
		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView("clientes/datosPersonales");
			return mav;
		}else {
			
			String nif = usuarioDTO.getNif();
			String nombre = usuarioDTO.getNombre();
			String apellido1 = usuarioDTO.getApellido1();
			String apellido2 = usuarioDTO.getApellido2();
			Date fechaNacimiento = usuarioDTO.getFechaNacimiento();
			String direccion = usuarioDTO.getDireccion();
			String poblacion = usuarioDTO.getPoblacion();
			String correo = usuarioDTO.getCorreo();
			String password = pswd;
			String cp = usuarioDTO.getCp();

			usuarioDTO = usuarioService.findById(usuarioDTO.getId());
			usuarioDTO.setNif(nif);
			usuarioDTO.setNombre(nombre);
			usuarioDTO.setApellido1(apellido1);
			usuarioDTO.setNombre(apellido2);
			usuarioDTO.setFechaNacimiento(fechaNacimiento);
			usuarioDTO.setDireccion(direccion);
			usuarioDTO.setPoblacion(poblacion);
			usuarioDTO.setCorreo(correo);
			usuarioDTO.setPassword(password);
			usuarioDTO.setCp(cp);

			usuarioService.save(usuarioDTO);

			ModelAndView mav = new ModelAndView("redirect:/perfil");
			return mav;
			
		}
		
		
	}
}
