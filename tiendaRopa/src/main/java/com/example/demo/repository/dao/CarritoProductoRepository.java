package com.example.demo.repository.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.repository.entity.Carrito;
import com.example.demo.repository.entity.CarritoProducto;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface CarritoProductoRepository extends JpaRepository<CarritoProducto, Long>{

	@Query(value = "SELECT cp FROM CarritoProducto cp WHERE cp.carrito.id = :idCarrito AND cp.producto.id = :idProducto")
	CarritoProducto findByIdCarritoyProducto(@Param("idCarrito")Long id, @Param("idProducto")Long id2);

	@Query(value = "SELECT cp FROM CarritoProducto cp WHERE cp.carrito.id = :idCarrito")
	Set<CarritoProducto> findAllByIdCarrito(@Param("idCarrito")Long id);

}
