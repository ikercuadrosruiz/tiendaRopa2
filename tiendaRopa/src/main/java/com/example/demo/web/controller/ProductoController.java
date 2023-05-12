package com.example.demo.web.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.dto.CategoriaDTO;
import com.example.demo.model.dto.ImagenDTO;
import com.example.demo.model.dto.ProductoDTO;
import com.example.demo.repository.entity.Imagen;
import com.example.demo.services.CategoriaService;
import com.example.demo.services.ImagenService;
import com.example.demo.services.ProductoService;

import jakarta.validation.Valid;

@Controller
public class ProductoController {

	private static final Logger log = LoggerFactory.getLogger(ProductoController.class);
	
	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private ImagenService imagenService;
	
	@Autowired
	private CategoriaService categoriaService;
	
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
		
		List<CategoriaDTO> listaCategoriasDTO = categoriaService.findAll();
		
		ModelAndView mav = new ModelAndView("trabajadores/form/productosForm");
		mav.addObject("productoDTO", new ProductoDTO());
		mav.addObject("listaCategoriasDTO", listaCategoriasDTO);
		mav.addObject("mod", false);
		
		return mav;
	}
	
	@GetMapping("/trabajadores/productos/update/{idProducto}")
	public ModelAndView update(@PathVariable Long idProducto) {
		
		log.info("ProductoController - update: Procedems a modificar el producto " + idProducto);
		
		ProductoDTO pDTO = productoService.findById(idProducto);
		List<CategoriaDTO> listaCategoriasDTO = categoriaService.findAll();
		
		ModelAndView mav = new ModelAndView("trabajadores/form/productosForm");
		mav.addObject("productoDTO", pDTO);
		mav.addObject("listaCategoriasDTO", listaCategoriasDTO);
		mav.addObject("mod", true);
		
		return mav;
	}
	
	@PostMapping("/trabajadores/productos/save")
	public ModelAndView save(@Valid @ModelAttribute("productoDTO") ProductoDTO pDTO, @RequestParam("file") MultipartFile img ,BindingResult result) {
		
		log.info("ProductoController - save: Guardamos el producto");
		
		if (!img.isEmpty()) {
			Path directorioImagenes = Paths.get("src/main/resources/static/images/productos");
			String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
			
			try {
				
				byte[] byteImg = img.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + img.getOriginalFilename());
				Files.write(rutaCompleta, byteImg);
						
				ImagenDTO iDTO = new ImagenDTO();
				iDTO.setNombre(img.getOriginalFilename());
				iDTO.setUrl(img.getOriginalFilename());
				
				pDTO.getListaImagenesDTO().set(0, iDTO);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
 		}
		
		ModelAndView mav;
		if (result.hasErrors()) {
			mav = new ModelAndView("trabajadores/form/productosForm");
		}else {
			productoService.save(pDTO);
			mav = new ModelAndView("redirect:/trabajadores/productos");
		}
		return mav;
	}
	
}
