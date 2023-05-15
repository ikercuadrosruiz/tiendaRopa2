package com.example.demo.repository.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.repository.entity.Categoria;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	@Query(value = "SELECT c FROM Categoria c WHERE c.nombre LIKE %:nombre%")
	List<Categoria> findAllByTerm(@Param("nombre") String searchTerm);

}
