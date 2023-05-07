package com.example.demo.services;

import java.util.List;

import com.example.demo.model.dto.ProductoDTO;

public interface ProductoService {

	List<ProductoDTO> findAll();

	void deleteById(Long idProducto);

	ProductoDTO findById(Long idProducto);

	void save(ProductoDTO pDTO);

	List<ProductoDTO> findAllODesc();

	List<ProductoDTO> findAllOAsc();

	List<ProductoDTO> findAllByCategoria(Long idCategoria);

}
