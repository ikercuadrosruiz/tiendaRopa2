package com.example.demo.services;

import java.util.ArrayList;

import com.example.demo.model.dto.CarritoProductoDTO;

public interface CarritoProductoService {

	void save(CarritoProductoDTO cpDTO);

	CarritoProductoDTO findByIdCarritoyProducto(Long id, Long id2);

	ArrayList<CarritoProductoDTO> findAllByIdCarrito(Long id);

	CarritoProductoDTO findById(Long idCarritoProducto);

	void delete(CarritoProductoDTO cpDTO);

}
