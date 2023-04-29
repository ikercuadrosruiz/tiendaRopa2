package com.example.demo.repository.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.repository.entity.Carrito;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface CarritoRepository  extends JpaRepository<Carrito, Long>{

	@Query(value = "SELECT c FROM Carrito c WHERE c.usuario.id = :idCli")
	Set<Carrito> findByIdCliente(@Param("idCli") Long id);

	@Query(value = "INSERT INTO carrito(fechageneracion, estado, idcliente) VALUES (CONVERT(datetime, :#{#c.fechageneracion}, 120), :#{#c.estado}, :#{#c.usuario.id})", nativeQuery = true)
	void saveByCarrito(@Param("c") Carrito cp);

}
