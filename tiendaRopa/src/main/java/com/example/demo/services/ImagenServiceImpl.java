package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.ImagenDTO;
import com.example.demo.repository.dao.ImagenRepository;
import com.example.demo.repository.entity.Imagen;

@Service
public class ImagenServiceImpl implements ImagenService {

	private static final Logger log = LoggerFactory.getLogger(ImagenServiceImpl.class);
	
	@Autowired
	private ImagenRepository imagenRepository;

	@Override
	public List<ImagenDTO> findAllImagesByProducto(Long idProducto) {
		
		log.info("ImagenServiceImpl - findAllImagesByProducto: Buscamos todas las imagenes del producto " + idProducto);
		
		List<Imagen> li = imagenRepository.findAllImagesByProducto(idProducto);
		
		List<ImagenDTO> liDTO = new ArrayList<ImagenDTO>();
		for (Imagen i : li) {
			liDTO.add(ImagenDTO.convertToDTO(i));
		}
		
		return liDTO;
	}
}
