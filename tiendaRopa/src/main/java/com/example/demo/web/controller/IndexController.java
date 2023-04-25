package com.example.demo.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.dto.ProductoDTO;
import com.example.demo.model.dto.UsuarioDTO;
import com.example.demo.services.UsuarioService;

import jakarta.validation.Valid;

@Controller
public class IndexController {

	private static final Logger log = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/trabajadores")
	public ModelAndView indexTrabajadores() {

		log.info("IndexController - IniciarAplicacion: Comienza la aplicacion (apartado de trabajadores)");

		ModelAndView mav = new ModelAndView("trabajadores/index");

		return mav;
	}

	@GetMapping("/index")
	public ModelAndView index() {

		log.info("IndexController - IniciarAplicacion: Comienza la aplicacion");

		ModelAndView mav = new ModelAndView("clientes/index");

		return mav;
	}

	/*
	@GetMapping("/login")
	public ModelAndView login() {

		log.info("IndexController - LogIn: Mostramos el formulario de login");

		ModelAndView mav = new ModelAndView("login");
		mav.addObject("usuarioDTO", new UsuarioDTO());

		return mav;
	}

	
	@PostMapping("/login/check")
	public ModelAndView comprobarDatosLogin(@Valid @ModelAttribute("usuarioDTO") UsuarioDTO uDTO,
			BindingResult result) {

		log.info("IndexController - comprobarDatosLogin: Comprobamos si existe el usuario " + "con email: "
				+ uDTO.getCorreo() + " y contraseña: " + uDTO.getPassword());

		ModelAndView mav = new ModelAndView();

		return mav;

	}
	*/

	@GetMapping("/registro")
	public ModelAndView registro() {

		log.info("IndexController - Registro: Mostramos el formulario de registro");

		ModelAndView mav = new ModelAndView("registro");
		mav.addObject("usuarioDTO", new UsuarioDTO());

		return mav;
	}

	@PostMapping("/registro/save")
	public ModelAndView registrarUsuario(@Valid @ModelAttribute("usuarioDTO") UsuarioDTO uDTO, BindingResult result) {

		log.info("IndexController - RegistrarUsuario: Añadimos el usuario a la BD");
		
		usuarioService.save(uDTO);

		ModelAndView mav = new ModelAndView("redirect:/registro?exito");
		return mav;
	}
}
