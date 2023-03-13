package com.example.demo.services;

import java.util.List;

import com.example.demo.model.dto.CategoriaDTO;
import com.example.demo.model.dto.ProductoDTO;

import jakarta.validation.Valid;

public interface CategoriaService {

	List<CategoriaDTO> findAll();

	void deleteById(Long idCategoria);

	CategoriaDTO findById(Long idCategoria);

	void save(@Valid CategoriaDTO cDTO);

}
