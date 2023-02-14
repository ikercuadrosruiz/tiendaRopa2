<<<<<<< HEAD
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
public class TrabajadorController {

	private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/trabajadores/trabajadores")
	public ModelAndView findAll() {

		log.info("UsuarioController - FindAll: Encontramos todos los trabajadores");

		List<UsuarioDTO> listaUsuariosDTO = usuarioService.findAllTrabajadores();

		ModelAndView mav = new ModelAndView("trabajadores/gestion/gestionTrabajadores");
		mav.addObject("listaUsuariosDTO", listaUsuariosDTO);

		return mav;
	}
	
	@GetMapping("/trabajadores/trabajadores/delete/{idUsuario}")
	public ModelAndView delete(@PathVariable Long idUsuario){
		 
		log.info("UsuarioController - delete: Borramos el trabajador " + idUsuario);
		
		usuarioService.deleteById(idUsuario);
		
		ModelAndView mav = new ModelAndView("redirect:/trabajadores/trabajadores");
		return mav;
		
	}
	
	@GetMapping("/trabajadores/trabajadores/{idUsuario}/update")
	public ModelAndView update(@PathVariable Long idUsuario) {
		
		log.info("UsuarioController - update: Actualizamos el trabajador");
		
		UsuarioDTO usuarioDTO = usuarioService.findById(idUsuario);
		
		ModelAndView mav = new ModelAndView("trabajadores/form/trabajadoresForm");
		mav.addObject("usuarioDTO", usuarioDTO);
		mav.addObject("mod", true);
		
		return mav;
	}
	
	@PostMapping("/trabajadores/trabajadores/save")
	public ModelAndView save(@ModelAttribute UsuarioDTO usuarioDTO) {
		
		log.info("UsuarioController - save: Guardamos el usuario");
		
		usuarioService.save(usuarioDTO);
		
		ModelAndView mav = new ModelAndView("redirect:/trabajadores/trabajadores");
		return mav;
	}
}
=======
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
public class TrabajadorController {
	private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/trabajadores/trabajadores")
	public ModelAndView findAll() {

		log.info("TrabajadoresController - FindAll: Encontramos todos los trabajador");

		List<UsuarioDTO> listaUsuariosDTO = usuarioService.findAllTrabajadores();

		ModelAndView mav = new ModelAndView("trabajadores/gestion/gestionTrabajadores");
		mav.addObject("listaUsuariosDTO", listaUsuariosDTO);

		return mav;
	}

	@GetMapping("/trabajadores/trabajadores/delete/{idUsuario}")
	public ModelAndView delete(@PathVariable Long idUsuario) {

		log.info("TrabajadoresController - delete: Borramos el trabajador " + idUsuario);

		usuarioService.deleteById(idUsuario);

		ModelAndView mav = new ModelAndView("redirect:/trabajadores/trabajadores");
		return mav;

	}

	@GetMapping("/trabajadores/trabajadores/{idUsuario}/update")
	public ModelAndView update(@PathVariable Long idUsuario) {

		log.info("TrabajadoresController - update: Actualizamos el trabajador");

		UsuarioDTO usuarioDTO = usuarioService.findById(idUsuario);

		ModelAndView mav = new ModelAndView("trabajadores/form/trabajadoresForm");
		mav.addObject("usuarioDTO", usuarioDTO);
		mav.addObject("mod", true);

		return mav;
	}
	
	@GetMapping("/trabajadores/trabajadores/add")
	public ModelAndView add() {

		log.info("TrabajadoresController - update: Actualizamos el trabajador");

		ModelAndView mav = new ModelAndView("trabajadores/form/trabajadoresForm");
		mav.addObject("usuarioDTO", new UsuarioDTO());
		mav.addObject("mod", false);

		return mav;
	}

	@PostMapping("/trabajadores/trabajadores/save")
	public ModelAndView save(@ModelAttribute UsuarioDTO usuarioDTO) {

		log.info("TrabajadoresController - save: Guardamos el trabajador");

		usuarioService.save(usuarioDTO);

		ModelAndView mav = new ModelAndView("redirect:/trabajadores/trabajadores");
		return mav;
	}
}
>>>>>>> branch 'main' of https://github.com/ikercuadrosruiz/tiendaRopa2.git
