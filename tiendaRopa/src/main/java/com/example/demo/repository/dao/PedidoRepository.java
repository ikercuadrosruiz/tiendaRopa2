package com.example.demo.repository.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.repository.entity.Pedido;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface PedidoRepository extends JpaRepository<Pedido, Long>{

	@Query(value = "SELECT p FROM Pedido p WHERE p.usuario.id = :idUsu")
	List<Pedido> findAllByUsuario(@Param("idUsu") Long idUsuario);

	@Query(value = "SELECT p FROM Pedido p WHERE p.numeroFactura = :nFac")
	Pedido findByNumeroDeFactura(@Param("nFac") String numeroFactura);
	
	

}
