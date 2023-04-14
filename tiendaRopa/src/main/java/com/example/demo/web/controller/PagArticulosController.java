package com.example.demo.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.services.UsuarioService;

@Controller
public class PagArticulosController {
	private static final Logger log = LoggerFactory.getLogger(IndexController.class);
	
	@GetMapping("/tienda")
	public ModelAndView inicioTienda() {

		log.info("PagArticulosController - PagArticulosController: Pasamos a la página con todos los artículos");

		ModelAndView mav = new ModelAndView("clientes/tienda");

		return mav;
	}
}
