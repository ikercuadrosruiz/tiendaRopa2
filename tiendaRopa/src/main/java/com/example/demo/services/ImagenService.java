package com.example.demo.services;

import java.util.List;

import com.example.demo.model.dto.ImagenDTO;

public interface ImagenService {

	List<ImagenDTO> findAllImagesByProducto(Long idProducto);

}
