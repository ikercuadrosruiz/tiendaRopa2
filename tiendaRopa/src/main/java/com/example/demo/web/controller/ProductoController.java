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
import com.example.demo.model.dto.UsuarioDTO;
import com.example.demo.services.ProductoService;

@Controller
public class ProductoController {

	private static final Logger log = LoggerFactory.getLogger(ProductoController.class);
	
	@Autowired
	private ProductoService productoService;
	
	@GetMapping("/trabajadores/productos")
	public ModelAndView findAll() {
		
		log.info("ProductoController - findAll: Encontramos todos los clientes");
		
		List<ProductoDTO> lpDTO = productoService.findAll();
		
		ModelAndView mav = new ModelAndView("trabajadores/gestion/gestionProductos");
		mav.addObject("listaProductosDTO", lpDTO);
		return mav;
	}
	
	@GetMapping("/trabajadores/productos/delete/{idProducto}")
	public ModelAndView delete(@PathVariable Long idProducto) {
		
		log.info("ProductoController - delete: Borramos el producto " + idProducto);
		
		productoService.deleteById(idProducto);
		
		ModelAndView mav = new ModelAndView("redirect:/trabajadores/productos");
		return mav;
	}
	
	@GetMapping("/trabajadores/productos/add")
	public ModelAndView add() {
		
		log.info("ProductoController - add: Procedems a añadir un nuevo producto");
		
		ModelAndView mav = new ModelAndView("trabajadores/form/productosForm");
		mav.addObject("productoDTO", new ProductoDTO());
		mav.addObject("mod", false);
		
		return mav;
	}
	
	@GetMapping("/trabajadores/productos/update/{idProducto}")
	public ModelAndView update(@PathVariable Long idProducto) {
		
		log.info("ProductoController - update: Procedems a modificar el producto " + idProducto);
		
		ProductoDTO pDTO = productoService.findById(idProducto);
		
		ModelAndView mav = new ModelAndView("trabajadores/form/productosForm");
		mav.addObject("productoDTO", pDTO);
		mav.addObject("mod", true);
		
		return mav;
	}
	
	@PostMapping("/trabajadores/productos/save")
	public ModelAndView save(@ModelAttribute ProductoDTO pDTO) {
		
		log.info("ProductoController - save: Guardamos el producto");
		
		productoService.save(pDTO);
		
		ModelAndView mav = new ModelAndView("redirect:/trabajadores/productos");
		return mav;
	}
	
}
