package com.example.demo.repository.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.repository.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{

	@Query(value = "SELECT p FROM Pedido p WHERE p.usuario.id = :idUsu")
	List<Pedido> findAllByUsuario(@Param("idUsu") Long idUsuario);

}
