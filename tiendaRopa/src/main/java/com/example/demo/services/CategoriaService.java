package com.example.demo.services;

import java.util.List;

import com.example.demo.model.dto.CategoriaDTO;

public interface CategoriaService {

	List<CategoriaDTO> findAll();

	void deleteById(Long idCategoria);

	CategoriaDTO findById(Long idCategoria);

}
