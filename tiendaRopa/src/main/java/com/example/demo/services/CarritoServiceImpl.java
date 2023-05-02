package com.example.demo.services;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.CarritoDTO;
import com.example.demo.model.dto.CarritoProductoDTO;
import com.example.demo.repository.dao.CarritoRepository;
import com.example.demo.repository.entity.Carrito;
import com.example.demo.repository.entity.CarritoProducto;

@Service
public class CarritoServiceImpl implements CarritoService {

	private static final Logger log = LoggerFactory.getLogger(CarritoServiceImpl.class);
	
	@Autowired
	private CarritoRepository cr;

	@Override
	public CarritoDTO findByIdCliente(Long id) {
		Set<Carrito> lc = cr.findByIdCliente(id);
		
		ArrayList<CarritoDTO> lcDTO = new ArrayList<CarritoDTO>();
		for (Carrito c : lc) {
			lcDTO.add(CarritoDTO.convertToDTO(c));
		}
		
		if (lcDTO.size() < 1) {
			return null;
		}else {return lcDTO.get(0);}
	}

	@Override
	public void save(CarritoDTO cDTO) {
		log.info("CarritoServiceImpl - save: Guardamos los datos del Carrito " + cDTO.toString());

		Carrito cp = CarritoDTO.convertToEntity(cDTO);
		cr.save(cp);
	}

	@Override
	public void saveByCarrito(CarritoDTO cDTO) {
		log.info("CarritoServiceImpl - save: Guardamos los datos del Carrito " + cDTO.toString());

		Carrito cp = CarritoDTO.convertToEntity(cDTO);
		cr.saveByCarrito(cp);
		
	}

	@Override
	public CarritoDTO findById(Long idCarrito) {
		log.info("CarritoServiceImpl - findById: Obtenemos carrito por su id " + idCarrito);
		
		Optional<Carrito> oc = cr.findById(idCarrito);
		
		if (oc.isPresent()) {
			return CarritoDTO.convertToDTO(oc.get());
		}else {
			return new CarritoDTO();
		}
	}

	@Override
	public void delete(Long idCarrito) {
		
		log.info("CarritoServiceImpl - delete: Borramos carrito por su id " + idCarrito);
		
		cr.deleteById(idCarrito);
		
	}
	
}
