package com.example.demo.repository.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.repository.entity.Imagen;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface ImagenRepository {

	@Query(value = "SELECT distinct i.* FROM imagenes i "
			+ "INNER JOIN imagenproducto ip ON i.id = ip.id "
			+ "INNER JOIN productos p ON :idP = ip.idproducto;", 
			nativeQuery = true)
	List<Imagen> findAllImagesByProducto(@Param("idP")Long idProducto);

}
