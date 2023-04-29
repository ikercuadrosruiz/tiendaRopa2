package com.example.demo.services;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.configuration.EncriptaPassword;
import com.example.demo.model.dto.CarritoDTO;
import com.example.demo.model.dto.CarritoProductoDTO;
import com.example.demo.model.dto.UsuarioDTO;
import com.example.demo.repository.dao.CarritoProductoRepository;
import com.example.demo.repository.dao.CarritoRepository;
import com.example.demo.repository.entity.Carrito;
import com.example.demo.repository.entity.CarritoProducto;
import com.example.demo.repository.entity.Usuario;

@Service
public class CarritoProductoServiceImpl implements CarritoProductoService {

private static final Logger log = LoggerFactory.getLogger(CarritoProductoServiceImpl.class);
	
	@Autowired
	private CarritoProductoRepository cr;

	@Override
	public void save(CarritoProductoDTO cpDTO) {
		log.info("CarritoProductoServiceImpl - save: Guardamos los datos del CarritoProducto " + cpDTO.toString());

		CarritoProducto cp = CarritoProductoDTO.convertToEntity(cpDTO);
		cr.save(cp);
	}

	@Override
	public CarritoProductoDTO findByIdCarritoyProducto(Long id, Long id2) {

		log.info("CarritoProductoServiceImpl - findByIdCarritoyProducto: Obtenemos el CarritoProducto por id del carrito y producto " + id + " - " + id2);
		
		CarritoProducto cp = cr.findByIdCarritoyProducto(id, id2);
		
		if (cp != null) {
			return CarritoProductoDTO.convertToDTO(cp);
		}else {return null;}	
	}

	@Override
	public ArrayList<CarritoProductoDTO> findAllByIdCarrito(Long id) {
		
		log.info("CarritoProductoServiceImpl - findAllByIdCarrito: Obtenemos los CarritoProductos por id del carrito " + id);
		
		Set<CarritoProducto> lcp = cr.findAllByIdCarrito(id);
		
		ArrayList<CarritoProductoDTO> lcpDTO = new ArrayList<CarritoProductoDTO>();
		for (CarritoProducto cp : lcp) {
			lcpDTO.add(CarritoProductoDTO.convertToDTO(cp));
		}
		
		return lcpDTO;
	}

	@Override
	public CarritoProductoDTO findById(Long idCarritoProducto) {
		log.info("CarritoProductoServiceImpl - findAllByIdCarrito: Obtenemos el carritoProducto con id " + idCarritoProducto);
		
		Optional<CarritoProducto> oCp = cr.findById(idCarritoProducto);
		
		if (oCp.isPresent()) {
			return CarritoProductoDTO.convertToDTO(oCp.get());
		}else {
			return new CarritoProductoDTO();
		}
		
	}

	@Override
	public void delete(CarritoProductoDTO cpDTO) {
		cr.deleteById(cpDTO.getId());
	}
	
}
