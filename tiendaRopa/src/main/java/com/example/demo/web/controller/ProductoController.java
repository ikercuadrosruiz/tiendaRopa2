package com.example.demo.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.dto.ProductoDTO;

@Controller
public class ProductoController {

	private static final Logger log = LoggerFactory.getLogger(ProductoController.class);
	
	//@Autowired
	//private ProductoService productoService;
	
	@GetMapping("/trabajadores/productos")
	public ModelAndView findAll() {
		
		log.info("ProductoController - findAll: Encontramos todos los clientes");
		
		// List<ProductoDTO> lpDTO = productoService.findAll();
		
		ModelAndView mav = new ModelAndView("trabajadores/gestion/gestionProductos");
		//mav.addObject("listaProductosDTO", lpDTO);
		return mav;
		
	}
	
}
