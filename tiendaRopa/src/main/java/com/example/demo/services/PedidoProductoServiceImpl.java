package com.example.demo.services;

import java.util.ArrayList;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.PedidoProductoDTO;
import com.example.demo.repository.dao.PedidoProductoRepository;
import com.example.demo.repository.dao.PedidoRepository;
import com.example.demo.repository.entity.PedidoProducto;

@Service
public class PedidoProductoServiceImpl implements PedidoProductoService {

private static final Logger log = LoggerFactory.getLogger(PedidoServiceImpl.class);
	
	@Autowired
	private PedidoProductoRepository ppr;
	
	@Override
	public void save(PedidoProductoDTO ppDTO) {
		
		log.info("PedidoServiceImpl - save: Guardamos los datos del pedidoProducto");
		
		PedidoProducto pp = PedidoProductoDTO.convertToEntity(ppDTO);
		ppr.save(pp);
		
	}

	@Override
	public ArrayList<PedidoProductoDTO> findAllByIdPedido(Long idPedido) {

		log.info("PedidoServiceImpl - findAllByIdPedido: Encontramos los productos por idPedido");
		
		Set<PedidoProducto> spp = ppr.findAllByIdPedido(idPedido);
		ArrayList<PedidoProductoDTO> lppDTO = new ArrayList<PedidoProductoDTO>();
		for (PedidoProducto pp : spp) {
			lppDTO.add(PedidoProductoDTO.convertToDTO(pp));
		}
		
		return lppDTO;
		
	}

}
