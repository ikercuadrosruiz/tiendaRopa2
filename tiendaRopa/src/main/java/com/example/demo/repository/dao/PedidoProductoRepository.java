package com.example.demo.repository.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.repository.entity.CarritoProducto;
import com.example.demo.repository.entity.PedidoProducto;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface PedidoProductoRepository extends JpaRepository<PedidoProducto, Long>{

	@Query(value = "SELECT pp FROM PedidoProducto pp WHERE pp.pedido.id = :idPedido")
	Set<PedidoProducto> findAllByIdPedido(@Param("idPedido") Long idPedido);

}
