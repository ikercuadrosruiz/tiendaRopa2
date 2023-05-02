package com.example.demo.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.dto.CarritoDTO;
import com.example.demo.model.dto.CarritoProductoDTO;
import com.example.demo.model.dto.PedidoProductoDTO;
import com.example.demo.model.dto.ProductoDTO;
import com.example.demo.model.dto.UsuarioDTO;
import com.example.demo.repository.entity.Carrito;
import com.example.demo.services.CarritoProductoService;
import com.example.demo.services.CarritoService;
import com.example.demo.services.CategoriaService;
import com.example.demo.services.ProductoService;
import com.example.demo.services.UsuarioService;

@Controller
public class CarritoController {

	
private static final Logger log = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private CarritoService caS;
	
	@Autowired
	private ProductoService ps;
	
	@Autowired
	private CarritoProductoService cps;
	
	@Autowired
	private CategoriaService cs;
	
	@Autowired
	private UsuarioService us;
	
	//CarritoDTO cDTO = new CarritoDTO();

	@GetMapping("/tienda/producto/addCarrito/{idProducto}")
	public ModelAndView addToCart(@PathVariable Long idProducto) {
		
		log.info("PagArticulosController - addToCart: Añadimos el producto al carrito");
		
		// Obtenemos el producto 
		ProductoDTO p = ps.findById(idProducto);
		
		// Obtener el usuario
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String correo = authentication.getName();
		UsuarioDTO uDTO = us.findByCorreo(correo); 
		
		// Si el carrito no está creado crear uno
		CarritoDTO cDTO = caS.findByIdCliente(uDTO.getId());
		if (cDTO == null) {
			cDTO = new CarritoDTO();
			cDTO.setUsuarioDTO(uDTO);
			cDTO.setFechageneracion(new Date());
			cDTO.setEstado(1);
			cDTO.setListaCarritoProductoDTO(new ArrayList<CarritoProductoDTO>());
			
			uDTO.getListaCarritosDTO().add(cDTO);
			
			caS.save/*ByCarrito*/(cDTO);
			cDTO = caS.findByIdCliente(uDTO.getId());
		}else {
			// Sino, obtenerlo de la base de datos
			uDTO.getListaCarritosDTO().add(cDTO);
		}
		
		// Relleno los datos del carritoProducto con el producto seleccionado
		// Si el carrito producto de ese objeto ya existe se realizar'a una b'usqueda para traerlo
		CarritoProductoDTO cpDTO = cps.findByIdCarritoyProducto(cDTO.getId(), p.getId());
		if (cpDTO == null) {
			cpDTO = new CarritoProductoDTO();
			cpDTO.setCarritoDTO(cDTO);
			cpDTO.setCantidad(1);
			cpDTO.setProductoDTO(p);
			cps.save(cpDTO);
		}else {
			// Sino, obtenerlo de la base de datos y actualizarle la cantidad
			cpDTO.setCantidad(cpDTO.getCantidad()+1);
			cps.save(cpDTO);
		}
		
		ModelAndView mav = new ModelAndView("redirect:/tienda?productoAdd");
		return mav;
	}
	
	
	@GetMapping("/tienda/carrito")
	public ModelAndView showCart() {
		
		log.info("PagArticulosController - showCart: Mostramos los productos en el carrito");
		
		// Obtener el usuario
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String correo = authentication.getName();
		UsuarioDTO uDTO = us.findByCorreo(correo); 
		
		// Si el carrito no está creado crear uno
		CarritoDTO cDTO = caS.findByIdCliente(uDTO.getId());
		if (cDTO == null) {
			cDTO = new CarritoDTO();
			cDTO.setUsuarioDTO(uDTO);
			cDTO.setFechageneracion(new Date());
			cDTO.setEstado(1);
			cDTO.setListaCarritoProductoDTO(new ArrayList<CarritoProductoDTO>());
			
			uDTO.getListaCarritosDTO().add(cDTO);
			
			caS.save/*ByCarrito*/(cDTO);
		}else {
			// Sino, obtenerlo de la base de datos
			uDTO.getListaCarritosDTO().add(cDTO);
		}
		
		// Buscar la lista de CarritoProducto del carrito
		ArrayList<CarritoProductoDTO> lcpDTO = cps.findAllByIdCarrito(cDTO.getId());
		
		Double subtotal = 0.0;
		for (CarritoProductoDTO cpDTO : lcpDTO) {
			subtotal = subtotal + (cpDTO.getProductoDTO().getPrecio() * cpDTO.getCantidad());
		}
		
		Double envio = 2.99;
		
		ModelAndView mav = new ModelAndView("clientes/vistaCarrito");
		mav.addObject("listaCarritoProductosDTO", lcpDTO);
		mav.addObject("subtotal", subtotal);
		mav.addObject("envio", envio);
		mav.addObject("total", subtotal+envio);
		
		return mav;
	}
	
	@GetMapping("/tienda/producto/carrito/minus/{idCarritoProducto}")
	public ModelAndView restarProductoDeCarrito(@PathVariable Long idCarritoProducto) {
		
		log.info("PagArticulosController - addToCart: Añadimos el producto al carrito");
		
		// Obtener el usuario
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String correo = authentication.getName();
		UsuarioDTO uDTO = us.findByCorreo(correo); 
		
		// Obtener carritoProducto y restar uno a la cantidad
		CarritoProductoDTO cpDTO = cps.findById(idCarritoProducto);
		cpDTO.setCantidad(cpDTO.getCantidad()-1);
		
		if (cpDTO.getCantidad() < 1) {
			cps.delete(cpDTO);
		}else {
			cps.save(cpDTO);
		}
		
		ModelAndView mav = new ModelAndView("redirect:/tienda/carrito");
		return mav;
	}
	
	
	@GetMapping("/tienda/producto/carrito/plus/{idCarritoProducto}")
	public ModelAndView sumarProductoDeCarrito(@PathVariable Long idCarritoProducto) {
		
		log.info("PagArticulosController - addToCart: Añadimos el producto al carrito");
		
		// Obtener el usuario
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String correo = authentication.getName();
		UsuarioDTO uDTO = us.findByCorreo(correo); 
		
		// Obtener carritoProducto y restar uno a la cantidad
		CarritoProductoDTO cpDTO = cps.findById(idCarritoProducto);
		cpDTO.setCantidad(cpDTO.getCantidad()+1);
		
		if (cpDTO.getCantidad() < 1) {
			cps.delete(cpDTO);
		}else {
			cps.save(cpDTO);
		}
		
		ModelAndView mav = new ModelAndView("redirect:/tienda/carrito");
		return mav;
	}
	
	@GetMapping("/tienda/producto/carrito/deleteAll/{idCarritoProducto}")
	public ModelAndView eliminarProductoDeCarrito(@PathVariable Long idCarritoProducto) {
		
		log.info("PagArticulosController - addToCart: Añadimos el producto al carrito");
		
		// Obtener el usuario
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String correo = authentication.getName();
		UsuarioDTO uDTO = us.findByCorreo(correo); 
		
		// Obtener carritoProducto y restar uno a la cantidad
		CarritoProductoDTO cpDTO = cps.findById(idCarritoProducto);
		cpDTO.setCantidad(0);
		
		cps.delete(cpDTO);
		
		ModelAndView mav = new ModelAndView("redirect:/tienda/carrito");
		return mav;
	}
}
