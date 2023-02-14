package com.example.demo.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class IndexController {
	
	private static final Logger log = LoggerFactory.getLogger(IndexController.class);
	
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
	
	@GetMapping("/index/login")
	public ModelAndView login() {
		
		log.info("IndexController - LogIn: Mostramos el login");
		
		ModelAndView mav = new ModelAndView("login");
		
		return mav;
	}

}
