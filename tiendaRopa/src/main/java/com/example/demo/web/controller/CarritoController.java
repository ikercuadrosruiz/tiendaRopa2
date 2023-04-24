package com.example.demo.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.dto.CarritoDTO;
import com.example.demo.model.dto.CarritoProductoDTO;
import com.example.demo.model.dto.PedidoProductoDTO;
import com.example.demo.model.dto.ProductoDTO;
import com.example.demo.model.dto.UsuarioDTO;
import com.example.demo.services.CarritoService;
import com.example.demo.services.CategoriaService;
import com.example.demo.services.ProductoService;

@Controller
public class CarritoController {

	
private static final Logger log = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private CarritoService caS;
	
	@Autowired
	private ProductoService ps;
	
	@Autowired
	private CategoriaService cs;
	
	CarritoDTO cDTO = new CarritoDTO();

	@GetMapping("/tienda/producto/addCarrito/{idProducto}")
	public ModelAndView addToCart(@PathVariable Long idProducto) {
		
		log.info("PagArticulosController - addToCart: Añadimos el producto al carrito");
		
		// Relleno los datos del carritoProducto con el producto seleccionado
		CarritoProductoDTO cpDTO = new CarritoProductoDTO();
		cpDTO.setCarritoDTO(cDTO);
		
		ProductoDTO pDTO = ps.findById(idProducto);
		cpDTO.setProductoDTO(pDTO);
		
		
		// Comprobamos que el articulo ya ha sido añadido al carrito
		// y en caso de que ya haya sido se le suma uno a la cantidad
		boolean ya_esta_carrito = false;
		for (CarritoProductoDTO c : cDTO.getListaCarritoProductoDTO()) {
				if (c.getProductoDTO().getId() == idProducto) {
					ya_esta_carrito = true;
					c.setCantidad(c.getCantidad()+1);
				}
		}
		
		
		// En caso de que no haya sido añadido el articulo antes
		// se añade al carrito
		if (!ya_esta_carrito) {
			cpDTO.setCantidad(1);
			cDTO.getListaCarritoProductoDTO().add(cpDTO);
		}
		
		
		ModelAndView mav = new ModelAndView("redirect:/clientes/tienda");
		return mav;
	}
}
