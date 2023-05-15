package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.CategoriaDTO;
import com.example.demo.model.dto.ProductoDTO;
import com.example.demo.model.dto.UsuarioDTO;
import com.example.demo.repository.dao.CategoriaRepository;
import com.example.demo.repository.dao.UsuarioRepository;
import com.example.demo.repository.entity.Categoria;
import com.example.demo.repository.entity.Usuario;

import jakarta.validation.Valid;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	private static final Logger log = LoggerFactory.getLogger(CategoriaServiceImpl.class);
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Override
	public List<CategoriaDTO> findAll() {
		log.info("CategoriaServiceImpl - FindAll: Encontramos todas las categorias");

		List<Categoria> listaCategorias = categoriaRepository.findAll();

		List<CategoriaDTO> listaCategoriasDTO = new ArrayList<CategoriaDTO>();
		for (Categoria c : listaCategorias) {
			listaCategoriasDTO.add(CategoriaDTO.convertToDTO(c));
		}

		return listaCategoriasDTO;
	}

	@Override
	public void deleteById(Long idCategoria) {
		log.info("CategoriaServiceImpl - deleteById: Borramos la categoria " + idCategoria);

		categoriaRepository.deleteById(idCategoria);

	}

	@Override
	public CategoriaDTO findById(Long idCategoria) {
		
		log.info("CategoriaServiceImpl - findById: Encontramos la categoria por id " + idCategoria);
		
		Optional<Categoria> c = categoriaRepository.findById(idCategoria);
		if (c.isPresent()) {
			CategoriaDTO cDTO = CategoriaDTO.convertToDTO(c.get());
			return cDTO;
		} else {
			return new CategoriaDTO();
		}
		
	}

	@Override
	public void save(@Valid CategoriaDTO cDTO) {
		
		log.info("CategoriaServiceImpl - save: Guardamos los datos de la categoria " + cDTO.getId() );
		
		categoriaRepository.save(CategoriaDTO.convertToEntity(cDTO));
	}

	@Override
	public List<CategoriaDTO> findAllByTerm(String searchTerm) {
		log.info("CategoriaServiceImpl - FindAll: Encontramos todas las categorias");

		List<Categoria> listaCategorias = categoriaRepository.findAllByTerm(searchTerm);

		List<CategoriaDTO> listaCategoriasDTO = new ArrayList<CategoriaDTO>();
		for (Categoria c : listaCategorias) {
			listaCategoriasDTO.add(CategoriaDTO.convertToDTO(c));
		}

		return listaCategoriasDTO;
	}
	
	

}
