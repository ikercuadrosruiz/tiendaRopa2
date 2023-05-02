package com.example.demo.services;

import org.springframework.stereotype.Service;

import com.example.demo.model.dto.CarritoDTO;


public interface CarritoService {

	CarritoDTO findByIdCliente(Long id);

	void save(CarritoDTO cDTO);

	void saveByCarrito(CarritoDTO cDTO);

	CarritoDTO findById(Long idCarrito);

	void delete(Long idCarrito);
}
