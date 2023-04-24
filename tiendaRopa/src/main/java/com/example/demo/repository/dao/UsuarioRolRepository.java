package com.example.demo.repository.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.repository.entity.Categoria;
import com.example.demo.repository.entity.UsuarioRol;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface UsuarioRolRepository extends JpaRepository<UsuarioRol, Long>{

	@Query(value = "SELECT ur FROM UsuarioRol ur WHERE ur.usuario.id = :idCli")
	List<UsuarioRol> findAllByIdCliente(@Param("idCli") Long id);

}
