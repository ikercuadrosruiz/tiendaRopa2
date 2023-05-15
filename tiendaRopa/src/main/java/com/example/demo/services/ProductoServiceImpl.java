package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.ProductoDTO;
import com.example.demo.repository.dao.PedidoRepository;
import com.example.demo.repository.dao.ProductoRepository;
import com.example.demo.repository.entity.Producto;

@Service
public class ProductoServiceImpl implements ProductoService {

	private static final Logger log = LoggerFactory.getLogger(ProductoServiceImpl.class);

	@Autowired
	private ProductoRepository productoRepository;

	@Override
	public List<ProductoDTO> findAll() {

		log.info("ProductoServiceImpl - FindAll: Encontramos todos los Productos");

		List<Producto> lp = productoRepository.findAll();

		List<ProductoDTO> lpDTO = new ArrayList<ProductoDTO>();
		for (Producto p : lp) {
			lpDTO.add(ProductoDTO.convertToDTO(p));
		}

		return lpDTO;
	}

	@Override
	public void deleteById(Long idProducto) {

		log.info("ProductoServiceImpl - deleteById: Borramos el producto " + idProducto);

		productoRepository.deleteById(idProducto);

	}

	@Override
	public ProductoDTO findById(Long idProducto) {

		log.info("ProductoServiceImpl - findById: Buscamoso producto por id" + idProducto);

		Optional<Producto> op = productoRepository.findById(idProducto);

		if (op.isPresent()) {
			return ProductoDTO.convertToDTO(op.get());
		} else {
			return null;
		}

	}

	@Override
	public void save(ProductoDTO pDTO) {

		log.info("ProductoServiceImpl - save: Guardamos los datos del producto " + pDTO.getId());

		productoRepository.save(ProductoDTO.convertToEntity(pDTO));

	}

	@Override
	public List<ProductoDTO> findAllODesc() {
		log.info("ProductoServiceImpl - findAllODesc: Encontramos todos los Productos");

		List<Producto> lp = productoRepository.findAllODesc();

		List<ProductoDTO> lpDTO = new ArrayList<ProductoDTO>();
		for (Producto p : lp) {
			lpDTO.add(ProductoDTO.convertToDTO(p));
		}

		return lpDTO;
	}

	@Override
	public List<ProductoDTO> findAllOAsc() {
		log.info("ProductoServiceImpl - findAllOAsc: Encontramos todos los Productos");

		List<Producto> lp = productoRepository.findAllOAsc();

		List<ProductoDTO> lpDTO = new ArrayList<ProductoDTO>();
		for (Producto p : lp) {
			lpDTO.add(ProductoDTO.convertToDTO(p));
		}

		return lpDTO;
	}

	@Override
	public List<ProductoDTO> findAllByCategoria(Long idCategoria) {
		log.info("ProductoServiceImpl - findAllByCategoria: Encontramos todos los Productos de la categoría "+idCategoria);

		List<Producto> lp = productoRepository.findAllByCategoria(idCategoria);

		List<ProductoDTO> lpDTO = new ArrayList<ProductoDTO>();
		for (Producto p : lp) {
			lpDTO.add(ProductoDTO.convertToDTO(p));
		}

		return lpDTO;
	}

	@Override
	public List<ProductoDTO> findAllWithStock() {
		log.info("ProductoServiceImpl - findAllWithStock: Encontramos todos los Productos que tengan stock disponible");

		List<Producto> lp = productoRepository.findAllWithStock();

		List<ProductoDTO> lpDTO = new ArrayList<ProductoDTO>();
		for (Producto p : lp) {
			lpDTO.add(ProductoDTO.convertToDTO(p));
		}

		return lpDTO;
	}

	@Override
	public List<ProductoDTO> findAllByTerm(String searchTerm) {
		log.info("ProductoServiceImpl - FindAll: Encontramos todos los Productos");

		List<Producto> lp = productoRepository.findAllByTerm(searchTerm);

		List<ProductoDTO> lpDTO = new ArrayList<ProductoDTO>();
		for (Producto p : lp) {
			lpDTO.add(ProductoDTO.convertToDTO(p));
		}

		return lpDTO;
	}

}
