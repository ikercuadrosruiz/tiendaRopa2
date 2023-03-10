package com.example.demo.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.dto.UsuarioDTO;
import com.example.demo.services.UsuarioService;

@Controller
public class UsuarioController {

	private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/trabajadores/usuarios")
	public ModelAndView findAll() {
		
		log.info("UsuarioController - FindAll: Encontramos todos los clientes");
		
		List<UsuarioDTO> listaUsuariosDTO = usuarioService.findAll();
		
		ModelAndView mav = new ModelAndView("trabajadores/gestion/gestionUsuarios");
		mav.addObject("listaUsuariosDTO", listaUsuariosDTO);
		
		return mav;
	}
	
	@GetMapping("/trabajadores/usuarios/delete/{idUsuario}")
	public ModelAndView delete(@PathVariable Long idUsuario){
		 
		log.info("UsuarioController - delete: Borramos el usuario " + idUsuario);
		
		usuarioService.deleteById(idUsuario);
		
		ModelAndView mav = new ModelAndView("redirect:/trabajadores/usuarios");
		return mav;
		
	}
	
	@GetMapping("/trabajadores/usuarios/update/{idUsuario}")
	public ModelAndView update(@PathVariable Long idUsuario) {
		
		log.info("UsuarioController - update: Actualizamos el usuario");
		
		UsuarioDTO usuarioDTO = usuarioService.findById(idUsuario);
		
		ModelAndView mav = new ModelAndView("trabajadores/form/usuariosForm");
		mav.addObject("usuarioDTO", usuarioDTO);
		mav.addObject("mod", true);
		
		return mav;
	}
	
	@PostMapping("/trabajadores/usuarios/save")
	public ModelAndView save(@ModelAttribute UsuarioDTO usuarioDTO) {
		
		log.info("UsuarioController - save: Guardamos el usuario");
		
		usuarioService.save(usuarioDTO);
		
		ModelAndView mav = new ModelAndView("redirect:/trabajadores/usuarios");
		return mav;
	}
}
