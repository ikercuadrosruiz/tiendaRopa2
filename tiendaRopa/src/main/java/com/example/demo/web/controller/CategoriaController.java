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

import com.example.demo.model.dto.CategoriaDTO;
import com.example.demo.model.dto.ProductoDTO;
import com.example.demo.services.CategoriaService;
import com.example.demo.services.ProductoService;

@Controller
public class CategoriaController {

	private static final Logger log = LoggerFactory.getLogger(CategoriaController.class);
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping("/trabajadores/categorias")
	public ModelAndView findAll() {
		
		log.info("CategoriaController - findAll: Encontramos todos los clientes");
		
		List<CategoriaDTO> lcDTO = categoriaService.findAll();
		
		ModelAndView mav = new ModelAndView("trabajadores/gestion/gestionCategorias");
		mav.addObject("listaCategoriasDTO", lcDTO);
		return mav;
	}
	
	@GetMapping("/trabajadores/categorias/delete/{idCategoria}")
	public ModelAndView delete(@PathVariable Long idCategoria) {
		
		log.info("CategoriaController - delete: Borramos la categoria " + idCategoria);
		
		categoriaService.deleteById(idCategoria);
		
		ModelAndView mav = new ModelAndView("redirect:/trabajadores/categorias");
		return mav;
	}
	
	
}
