package com.example.demo.services;

import java.util.ArrayList;

import com.example.demo.model.dto.PedidoProductoDTO;

public interface PedidoProductoService {

	void save(PedidoProductoDTO ppDTO);

	ArrayList<PedidoProductoDTO> findAllByIdPedido(Long idPedido);

}
