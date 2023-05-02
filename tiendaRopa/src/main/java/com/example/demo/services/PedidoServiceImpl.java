package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.PedidoDTO;
import com.example.demo.repository.dao.PedidoRepository;
import com.example.demo.repository.entity.Pedido;

@Service
public class PedidoServiceImpl implements PedidoService{
	
	private static final Logger log = LoggerFactory.getLogger(PedidoServiceImpl.class);
	
	@Autowired
	private PedidoRepository pedidoRepository;

	@Override
	public List<PedidoDTO> findAllByUsuario(Long idUsuario) {
		
		log.info("PedidoServiceImpl - findAllByUsuario: Buscamos pedidos por usuario " + idUsuario);
		
		List<Pedido> listaPedidos = pedidoRepository.findAllByUsuario(idUsuario);
		
		List<PedidoDTO> listaPedidosDTO = new ArrayList<PedidoDTO>();
		for (Pedido p : listaPedidos) {
			listaPedidosDTO.add(PedidoDTO.convertToDTO(p));
		}
		
		return listaPedidosDTO;
	}

	@Override
	public List<PedidoDTO> findAll() {
		
		log.info("PedidoServiceImpl - findAll: Buscamos pedidos");
		
		List<Pedido> listaPedidos = pedidoRepository.findAll();
		
		List<PedidoDTO> listaPedidosDTO = new ArrayList<PedidoDTO>();
		for (Pedido p : listaPedidos) {
			listaPedidosDTO.add(PedidoDTO.convertToDTO(p));
		}
		
		return listaPedidosDTO;
	}

	@Override
	public void deleteById(Long idPedido) {
		
		log.info("PedidoServiceImpl - deleteById: Borramos el pedido " + idPedido);
		
		pedidoRepository.deleteById(idPedido);
		
	}

	@Override
	public PedidoDTO findById(Long idPedido) {
		log.info("PedidoServiceImpl - findById: Encontramos el pedido " + idPedido);
		
		Optional<Pedido> op = pedidoRepository.findById(idPedido);
		
		if (op.isPresent()) {
			PedidoDTO pDTO = PedidoDTO.convertToDTO(op.get());
			return pDTO;
		}else {
			return null;
		}
		
	}

	@Override
	public void save(PedidoDTO pDTO) {
			
		Pedido p = PedidoDTO.convertToEntity(pDTO);
		
		pedidoRepository.save(p);
	}

	@Override
	public PedidoDTO findByNumeroFactura(String numeroFactura) {
		
		log.info("PedidoServiceImpl - findByNumeroFactura: Encontramos el pedido por su numero de factura " + numeroFactura);
		
		Pedido p = pedidoRepository.findByNumeroDeFactura(numeroFactura);
		
		return PedidoDTO.convertToDTO(p);
		
	}
	

	
}
