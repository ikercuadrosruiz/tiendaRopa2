package com.example.demo.web.controller;

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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.dto.CarritoDTO;
import com.example.demo.model.dto.CarritoProductoDTO;
import com.example.demo.model.dto.PedidoDTO;
import com.example.demo.model.dto.PedidoProductoDTO;
import com.example.demo.model.dto.RolDTO;
import com.example.demo.model.dto.UsuarioDTO;
import com.example.demo.services.CarritoProductoService;
import com.example.demo.services.CarritoService;
import com.example.demo.services.PayPalService;
import com.example.demo.services.PedidoProductoService;
import com.example.demo.services.PedidoService;
import com.example.demo.services.UsuarioService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;

import jakarta.validation.Valid;

@Controller
public class PayPalController {

	private static final Logger log = LoggerFactory.getLogger(PayPalController.class);
	
	public static final String SUCCESS_URL = "pay/success";
	public static final String CANCEL_URL = "pay/cancel";
	
	@Autowired 
	private PayPalService pps;
	
	@Autowired 
	private PedidoService pes;
	
	@Autowired 
	private PedidoProductoService pepos;
	
	@Autowired 
	private UsuarioService us;
	
	@Autowired 
	private CarritoService cs;
	
	@Autowired 
	private CarritoProductoService caprs;
	
	@PostMapping("/pasarelaPago")
	public ModelAndView payment(@Valid @ModelAttribute("usuarioDTO") UsuarioDTO uDTO, BindingResult result, @ModelAttribute(value="carritoDTO") CarritoDTO carritoDTO) {
		
		log.info("PayPalController - payment:Se procede al pago");
		
		if (result.hasErrors()) {
			
			uDTO = us.findById(uDTO.getId());
			
			// Obtengo el carrito
			CarritoDTO cDTO = cs.findByIdCliente(uDTO.getId());
			ArrayList<CarritoProductoDTO> lcpDTO = caprs.findAllByIdCarrito(cDTO.getId());
			cDTO.setListaCarritoProductoDTO(lcpDTO);
			
			Double subtotal = 0.0;
			for (CarritoProductoDTO cpDTO : lcpDTO) {
				subtotal = subtotal + (cpDTO.getProductoDTO().getPrecio() * cpDTO.getCantidad());
			}
			Double envio = 2.99;
			
			ModelAndView mav = new ModelAndView("clientes/tramitarPedido");
			mav.addObject("carritoDTO", cDTO);
			mav.addObject("subtotal", subtotal);
			mav.addObject("envio", envio);
			mav.addObject("total", subtotal+envio);
			
			return mav;
			
		}else {
			
			Double total = 0.0;
			String currency = "EUR";
			String method = "paypal";
			String intent = "sale";
			String description = "This is a description";
			
			
			// ===========================================
			// Obtención de USUARIO, CARRITO y total
			// ===========================================
			// Obtengo el cliente al completo
			uDTO = us.findById(uDTO.getId());
			
			// Obtengo el carrito del cliente al completo
			CarritoDTO cDTO = cs.findByIdCliente(uDTO.getId());
			ArrayList<CarritoProductoDTO> lcpDTO = caprs.findAllByIdCarrito(cDTO.getId());
			cDTO.setListaCarritoProductoDTO(lcpDTO);
			
			// Calculo el total
			Double envio = 2.99;
			Double subtotal = 0.0;
			for (CarritoProductoDTO cpDTO : lcpDTO) {
				subtotal = subtotal + (cpDTO.getProductoDTO().getPrecio() * cpDTO.getCantidad());
			}
			total = envio + subtotal;
			
			// ===========================================
			// Realizar el pago
			// ===========================================
			try {
				//Realizar el pago
				Payment payment = pps.createPayment(total, currency, method, intent, description, "http://localhost:8888/fruttidivestiti/"+CANCEL_URL, "http://localhost:8888/fruttidivestiti/"+SUCCESS_URL);
				
				for (Links link : payment.getLinks()) {
					if (link.getRel().equals("approval_url")) {
						return new ModelAndView("redirect:" + link.getHref());
					}
				}
				
				return new ModelAndView("clientes/index");
				
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			return new ModelAndView("clientes/index");
			
		}
	}
	
	@GetMapping(value = CANCEL_URL)
	public ModelAndView cancelPay() {
		ModelAndView mav = new ModelAndView("redirect:/tienda?cancelPayment");
		return mav;
	}
	
	@GetMapping(value = SUCCESS_URL)
	public ModelAndView successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
		
		try {
			
			Payment payment = pps.executePayment(paymentId, payerId);
			System.out.println(payment.toJSON());
			
			if (payment.getState().equals("approved")) {
				
				// Transformamos el carrito a pedido
				Double total = 0.0;
				
				// ===========================================
				// Obtención de USUARIO, CARRITO y total
				// ===========================================
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				String correo = authentication.getName();
				UsuarioDTO uDTO = us.findByCorreo(correo); 
				
				// Obtengo el carrito del cliente al completo
				CarritoDTO cDTO = cs.findByIdCliente(uDTO.getId());
				ArrayList<CarritoProductoDTO> lcpDTO = caprs.findAllByIdCarrito(cDTO.getId());
				cDTO.setListaCarritoProductoDTO(lcpDTO);
				
				// Calculo el total
				Double envio = 2.99;
				Double subtotal = 0.0;
				for (CarritoProductoDTO cpDTO : lcpDTO) {
					subtotal = subtotal + (cpDTO.getProductoDTO().getPrecio() * cpDTO.getCantidad());
				}
				total = envio + subtotal;
				
				// ===========================================
				// Transformación de CarritoDTO a PedidoDTO
				// ===========================================
				PedidoDTO pDTO = new PedidoDTO();
				String numeroFactura = generarNumeroFactura();
				pDTO.setNumeroFactura(numeroFactura);
				pDTO.setPrecio(total.floatValue());
				pDTO.setEstado("en curso");
				pDTO.setFechaEmision(new java.util.Date());
				pDTO.setFechaEntrega(new java.util.Date());
				pDTO.setUsuarioDTO(uDTO);
				pDTO.setListaPedidoProductoDTO(new ArrayList<PedidoProductoDTO>());
				pes.save(pDTO);
				
				PedidoDTO pDTO2 = pes.findByNumeroFactura(pDTO.getNumeroFactura());
				
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
				cs.delete(cDTO.getId());
				
				// Informamos al usuario del éxito en el pago
				return new ModelAndView("redirect:/tienda?successPayment");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		ModelAndView mav = new ModelAndView("redirect:/tienda?cancelPayment");
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
