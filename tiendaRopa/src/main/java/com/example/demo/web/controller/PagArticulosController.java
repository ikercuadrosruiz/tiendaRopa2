package com.example.demo.web.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
import com.example.demo.model.dto.CategoriaDTO;
import com.example.demo.model.dto.PedidoDTO;
import com.example.demo.model.dto.PedidoProductoDTO;
import com.example.demo.model.dto.ProductoDTO;
import com.example.demo.model.dto.UsuarioDTO;
import com.example.demo.repository.entity.Pedido;
import com.example.demo.repository.entity.PedidoProducto;
import com.example.demo.services.CarritoProductoService;
import com.example.demo.services.CarritoService;
import com.example.demo.services.CategoriaService;
import com.example.demo.services.PedidoProductoService;
import com.example.demo.services.PedidoService;
import com.example.demo.services.ProductoService;
import com.example.demo.services.UsuarioService;

@Controller
public class PagArticulosController {

	private static final Logger log = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private ProductoService ps;
	
	@Autowired
	private PedidoProductoService pepos;
	
	@Autowired
	private CarritoProductoService caprs;
	
	@Autowired
	private PedidoService pes;

	@Autowired
	private CategoriaService cs;

	@Autowired
	private CarritoService cas;

	@Autowired
	private UsuarioService us;

	@GetMapping("/tienda")
	public ModelAndView inicioTienda() {

		log.info("PagArticulosController - inicioTienda: Pasamos a la página con todos los artículos");

		List<ProductoDTO> lpDTO = ps.findAll();
		List<CategoriaDTO> lcDTO = cs.findAll();

		ModelAndView mav = new ModelAndView("clientes/tienda");
		mav.addObject("listaProductosDTO", lpDTO);
		mav.addObject("listaCategoriasDTO", lcDTO);

		return mav;
	}
	
	@GetMapping("/tienda/1")
	public ModelAndView inicioTiendaOrdenadaDesc() {
		// De + a -

		log.info("PagArticulosController - inicioTiendaOrdenadaDesc: Pasamos a la página con todos los artículos ordenados");

		List<ProductoDTO> lpDTO = ps.findAllODesc();
		List<CategoriaDTO> lcDTO = cs.findAll();

		ModelAndView mav = new ModelAndView("clientes/tienda");
		mav.addObject("listaProductosDTO", lpDTO);
		mav.addObject("listaCategoriasDTO", lcDTO);

		return mav;
	}
	
	@GetMapping("/tienda/2")
	public ModelAndView inicioTiendaOrdenadaAsc() {
		// De - a +

		log.info("PagArticulosController - inicioTiendaOrdenadaAsc: Pasamos a la página con todos los artículos ordenados");

		List<ProductoDTO> lpDTO = ps.findAllOAsc();
		List<CategoriaDTO> lcDTO = cs.findAll();

		ModelAndView mav = new ModelAndView("clientes/tienda");
		mav.addObject("listaProductosDTO", lpDTO);
		mav.addObject("listaCategoriasDTO", lcDTO);

		return mav;
	}
	
	@GetMapping("/tienda/catergoria/{idCategoria}")
	public ModelAndView inicioTiendaXCategoria(@PathVariable Long idCategoria) {
		// Por categoria

		log.info("PagArticulosController - inicioTiendaOrdenadaAsc: Pasamos a la página con todos los artículos ordenados");

		List<ProductoDTO> lpDTO = ps.findAllByCategoria(idCategoria);
		List<CategoriaDTO> lcDTO = cs.findAll();

		ModelAndView mav = new ModelAndView("clientes/tienda");
		mav.addObject("listaProductosDTO", lpDTO);
		mav.addObject("listaCategoriasDTO", lcDTO);

		return mav;
	}

	@GetMapping("/tienda/producto/{idProducto}")
	public ModelAndView verProducto(@PathVariable Long idProducto) {

		log.info("PagArticulosController - verProducto: Pasamos a la página de ver el articulo con id " + idProducto);

		ProductoDTO pDTO = ps.findById(idProducto);

		ModelAndView mav = new ModelAndView("clientes/mostrarArt");
		mav.addObject("productoDTO", pDTO);

		return mav;
	}

	/*
	@GetMapping("/tienda/carrito/{idCarrito}/tramite")
	public ModelAndView transformarCarritoAPedido(@PathVariable Long idCarrito) {

		log.info("PagArticulosController - verTramitarPedido: Pasamos a la página de tramitar el pedido del carrito " + idCarrito );
		
		// Convertimos el carrito en pedido
		// Obtenemos el usuario
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String correo = authentication.getName();
		UsuarioDTO uDTO = us.findByCorreo(correo); 
		
		// Obtengo el carrito
		CarritoDTO cDTO = cas.findById(idCarrito);
		ArrayList<CarritoProductoDTO> lcpDTO = caprs.findAllByIdCarrito(idCarrito);
		cDTO.setListaCarritoProductoDTO(lcpDTO);
		
		// Calculamos el numero de factura
		String numeroFactura = generarNumeroFactura();
		
		// Calculamos el precio total
		Float envio = 2.99f;
		Float precioTotal = 0f;
		for (CarritoProductoDTO cpDTO : cDTO.getListaCarritoProductoDTO()) {
			precioTotal = precioTotal + (cpDTO.getCantidad() * cpDTO.getProductoDTO().getPrecio());
		}
		precioTotal += envio;
		
		//Transformamos el CarritoDTO a un PedidoDTO y lo guardamos en la base
		PedidoDTO pDTO = new PedidoDTO();
		pDTO.setNumeroFactura(numeroFactura);
		pDTO.setPrecio(precioTotal);
		pDTO.setEstado("en curso");
		pDTO.setFechaEmision(new java.util.Date());
		pDTO.setFechaEntrega(new java.util.Date());
		pDTO.setUsuarioDTO(uDTO);
		pDTO.setListaPedidoProductoDTO(new ArrayList<PedidoProductoDTO>());
		pes.save(pDTO);
		
		
		// Recogemos el pedido de la base de datos para obtener su id
		PedidoDTO pDTO2 = pes.findByNumeroFactura(pDTO.getNumeroFactura());
		
		// Tranformamos la lista de CarritoProductoDTO a PedidoProductoDTO
		ArrayList<PedidoProductoDTO> listaPedidoProductoDTO = new ArrayList<PedidoProductoDTO>();
		for (CarritoProductoDTO cpDTO : cDTO.getListaCarritoProductoDTO()) {
			PedidoProductoDTO ppDTO = new PedidoProductoDTO();
			ppDTO.setCantidad(cpDTO.getCantidad());
			ppDTO.setProductoDTO(cpDTO.getProductoDTO());
			ppDTO.setPedidoDTO(pDTO2);
			listaPedidoProductoDTO.add(ppDTO);
				
			//Guardamos el PedidoProductoDTO
			pepos.save(ppDTO);
		}

		pDTO2.setListaPedidoProductoDTO(listaPedidoProductoDTO);
		
		
		// Borramos el carrito
		cas.delete(idCarrito);
		
		ModelAndView mav = new ModelAndView("clientes/pedidos");
		mav.addObject("pedidoDTO", pDTO2);

		return mav;
	}
	*/
	
	@GetMapping("/tienda/carrito/{idCarrito}/tramite")
	public ModelAndView tramitarPedido(@PathVariable Long idCarrito) {

		log.info("PagArticulosController - verTramitarPedido: Pasamos a la página de tramitar el pedido del carrito " + idCarrito );
		
		// Convertimos el carrito en pedido
		// Obtenemos el usuario
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String correo = authentication.getName();
		UsuarioDTO uDTO = us.findByCorreo(correo); 
		
		// Obtengo el carrito
		CarritoDTO cDTO = cas.findById(idCarrito);
		ArrayList<CarritoProductoDTO> lcpDTO = caprs.findAllByIdCarrito(idCarrito);
		cDTO.setListaCarritoProductoDTO(lcpDTO);
		
		Double subtotal = 0.0;
		for (CarritoProductoDTO cpDTO : lcpDTO) {
			subtotal = subtotal + (cpDTO.getProductoDTO().getPrecio() * cpDTO.getCantidad());
		}
		Double envio = 2.99;
		
		ModelAndView mav = new ModelAndView("clientes/tramitarPedido");
		mav.addObject("carritoDTO", cDTO);
		mav.addObject("usuarioDTO", uDTO);
		mav.addObject("subtotal", subtotal);
		mav.addObject("envio", envio);
		mav.addObject("total", subtotal+envio);

		return mav;
	}

	@GetMapping("/perfil")
	public ModelAndView verPerfil() {

		log.info("PagArticulosController - verPerfil: Pasamos a la página de ver el perfil");

		// Obtener el usuario
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String correo = authentication.getName();
		UsuarioDTO uDTO = us.findByCorreo(correo);

		ModelAndView mav = new ModelAndView("clientes/datosPersonales");
		mav.addObject("usuarioDTO", uDTO);

		return mav;
	}

	@GetMapping("/pedidos")
	public ModelAndView verPedidos() {

		log.info("PagArticulosController - verPedidos: Pasamos a la página de ver pedidos");

		// Obtener el usuario
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String correo = authentication.getName();
		UsuarioDTO uDTO = us.findByCorreo(correo);
		
		// Obtenemos la lista de pedidos del ususario
		List<PedidoDTO> lpDTO = pes.findAllByUsuario(uDTO.getId());

		ModelAndView mav = new ModelAndView("clientes/pedidos");
		mav.addObject("usuarioDTO", uDTO);
		mav.addObject("listaPedidosDTO", lpDTO);

		return mav;
	}
	
	
	@GetMapping("/pedidos/{idPedido}")
	public ModelAndView verPedidoSimple(@PathVariable Long idPedido) {

		log.info("PagArticulosController - verPedidoSimple: Pasamos a la página del pedido");
		
		// Buscamos el pedido en la Base de Datos
		PedidoDTO pDTO = pes.findById(idPedido);
		
		// Buscamos la lista de productos del pedido
		ArrayList<PedidoProductoDTO> lppDTO = pepos.findAllByIdPedido(idPedido);
		
		pDTO.setListaPedidoProductoDTO(lppDTO);

		ModelAndView mav = new ModelAndView("clientes/pedidoSimple");
		mav.addObject("pedidoDTO", pDTO);
		mav.addObject("listaPedidoProductoDTO", lppDTO);
		
		return mav;
	}
	
	public static String generarNumeroFactura() {
		java.util.Date fecha = new java.util.Date();
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Random generadorAleatorio = new Random();
		int numeroAleatorio = generadorAleatorio.nextInt(1000000); // Generar número aleatorio de 6 dígitos

		String numeroFactura = formatoFecha.format(fecha) + String.format("%06d", numeroAleatorio);

		return numeroFactura;
	}

}
