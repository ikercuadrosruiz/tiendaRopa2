package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.PedidoDTO;
import com.example.demo.model.dto.UsuarioDTO;
import com.example.demo.repository.dao.PedidoRepository;
import com.example.demo.repository.entity.Pedido;

@Service
public class PedidoServiceImpl implements PedidoService{
	
	@Autowired
	private PedidoRepository pedidoRepository;

	@Override
	public List<PedidoDTO> findAllByUsuario(Long idUsuario) {
		List<Pedido> listaPedidos = pedidoRepository.findAllByUsuario(idUsuario);
		
		List<PedidoDTO> listaPedidosDTO = new ArrayList<PedidoDTO>();
		for (Pedido p : listaPedidos) {
			listaPedidosDTO.add(PedidoDTO.convertToDTO(p));
		}
		
		return listaPedidosDTO;
	}

	
}
