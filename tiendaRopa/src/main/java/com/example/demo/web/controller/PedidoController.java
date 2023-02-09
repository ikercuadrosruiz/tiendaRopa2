package com.example.demo.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.dto.PedidoDTO;
import com.example.demo.model.dto.UsuarioDTO;
import com.example.demo.services.PedidoService;
import com.example.demo.services.UsuarioService;

@Controller
public class PedidoController {

	private static final Logger log = LoggerFactory.getLogger(PedidoController.class);
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/trabajadores/pedidos")
	public ModelAndView findAll(){
		
		log.info("PedidoController - FindAllByCliente: Encontramos todos los pedidos");
		
		List<PedidoDTO> listaPedidosDTO = pedidoService.findAll();
		
		ModelAndView mav = new ModelAndView("trabajadores/gestion/gestionPedidos");
		mav.addObject("listaPedidosDTO", listaPedidosDTO);
		mav.addObject("fromUsuarios", false);
		return mav;
		
	}
	
	@GetMapping("/trabajadores/usuarios/{idUsuario}/pedidos")
	public ModelAndView findAllByCliente(@PathVariable Long idUsuario) {
		
		log.info("PedidoController - FindAllByCliente: Encontramos todos los pedidos del Usuario " + idUsuario);
		
		UsuarioDTO usuarioDTO = usuarioService.findById(idUsuario);
		List<PedidoDTO> listaPedidosDTO = pedidoService.findAllByUsuario(idUsuario);
		
		ModelAndView mav = new ModelAndView("trabajadores/gestion/gestionPedidos");
		mav.addObject("listaPedidosDTO", listaPedidosDTO);
		mav.addObject("usuarioDTO", usuarioDTO);
		mav.addObject("fromUsuarios", true); // Indica al html que viene de la previa pagina de usuarios
		return mav;
	}
	
	@GetMapping("/trabajadores/pedidos/delete/{idPedido}")
	public ModelAndView delete(@PathVariable Long idPedido) {
		
		log.info("PedidoController - delete: Borramos el pedido del usuario " + idPedido);
		
		pedidoService.deleteById(idPedido);
		
		ModelAndView mav = new ModelAndView("redirect:/trabajadores/pedidos");
		return mav;
		
	}
}
