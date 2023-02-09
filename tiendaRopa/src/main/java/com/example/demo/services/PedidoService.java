package com.example.demo.services;

import java.util.List;

import com.example.demo.model.dto.PedidoDTO;

public interface PedidoService {

	List<PedidoDTO> findAllByUsuario(Long idUsuario);

	List<PedidoDTO> findAll();

	void deleteById(Long idPedido);

}
