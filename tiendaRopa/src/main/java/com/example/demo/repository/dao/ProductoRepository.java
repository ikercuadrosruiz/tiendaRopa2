package com.example.demo.repository.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.repository.entity.Producto;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface ProductoRepository extends JpaRepository<Producto, Long> {

	@Query(value = "SELECT p FROM Producto p WHERE p.stock > 0 ORDER BY p.precio DESC")
	List<Producto> findAllODesc();

	@Query(value = "SELECT p FROM Producto p WHERE p.stock > 0 ORDER BY p.precio ASC")
	List<Producto> findAllOAsc();

	@Query(value = "SELECT p FROM Producto p WHERE p.categoria.id = :idCategoria AND p.stock > 0")
	List<Producto> findAllByCategoria(@Param("idCategoria") Long idCategoria);

	@Query(value = "SELECT p FROM Producto p WHERE p.stock > 0")
	List<Producto> findAllWithStock();

	@Query(value = "SELECT p FROM Producto p WHERE p.nombre LIKE %:nombre%")
	List<Producto> findAllByTerm(@Param("nombre") String searchTerm);

}
