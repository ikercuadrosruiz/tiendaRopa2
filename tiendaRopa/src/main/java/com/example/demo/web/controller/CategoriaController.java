package com.example.demo.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.dto.CategoriaDTO;
import com.example.demo.model.dto.ProductoDTO;
import com.example.demo.model.dto.UsuarioDTO;
import com.example.demo.services.CategoriaService;
import com.example.demo.services.ProductoService;

import jakarta.validation.Valid;

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
	
	@GetMapping("/trabajadores/categorias/update/{idCategoria}")
	public ModelAndView update(@PathVariable Long idCategoria) {
		
		log.info("CategoriaController - update: Modificamos la categoria " + idCategoria);
		
		CategoriaDTO cDTO = categoriaService.findById(idCategoria);
		
		ModelAndView mav = new ModelAndView("trabajadores/form/categoriasForm");
		mav.addObject("categoriaDTO", cDTO);
		mav.addObject("mod", true);
		return mav;
	}
	
	@GetMapping("/trabajadores/categorias/add")
	public ModelAndView add() {
		
		log.info("CategoriaController - add: Añadimos una nueva categoria");
		
		CategoriaDTO cDTO = new CategoriaDTO();
		
		ModelAndView mav = new ModelAndView("trabajadores/form/categoriasForm");
		mav.addObject("categoriaDTO", cDTO);
		mav.addObject("mod", false);
		mav.addObject("add", true);
		
		return mav;
	}
	
	@PostMapping("/trabajadores/categorias/save")
	public ModelAndView save(@Valid @ModelAttribute("categoriaDTO") CategoriaDTO cDTO, BindingResult result) {
		
		log.info("CategoriaController - save: Guardamos la categoria");
		
		ModelAndView mav;
		if (result.hasErrors()) {
			mav = new ModelAndView("trabajadores/form/categoriasForm");
			mav.addObject("categoriaDTO", cDTO);
			mav.addObject("mod", true);
			
		}else {
			categoriaService.save(cDTO);
			mav = new ModelAndView("redirect:/trabajadores/categorias");
		}
		return mav;
	}
	
	@GetMapping("/trabajadores/categorias/search")
	public ModelAndView buscarCategorias(@RequestParam("term") String searchTerm) {
		
		log.info("CategoriasController - buscarCategorias: Encontramos todas las categorais segun parametro");

		List<CategoriaDTO> listaCategoriasDTO = categoriaService.findAllByTerm(searchTerm);

		ModelAndView mav = new ModelAndView("trabajadores/gestion/gestionCategorias");
		mav.addObject("listaCategoriasDTO", listaCategoriasDTO);
		
		return mav;
	}
	
}
