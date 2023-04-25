package com.example.demo.repository.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.repository.entity.Rol;
import com.example.demo.repository.entity.Usuario;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface RolRepository extends JpaRepository<Rol, Long> {

	@Query(value = "SELECT r FROM Usuario u JOIN u.listaRoles r WHERE u.id = :idCli")
	Set<Rol> findAllByIdCliente(@Param("idCli") Long idCliente);
}
