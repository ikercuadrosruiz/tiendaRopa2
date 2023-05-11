package com.example.demo.services;

import java.util.List;

import com.example.demo.model.dto.RolDTO;

public interface RolService {

	List<RolDTO> findAll();

	RolDTO findByRol(String r);

}
