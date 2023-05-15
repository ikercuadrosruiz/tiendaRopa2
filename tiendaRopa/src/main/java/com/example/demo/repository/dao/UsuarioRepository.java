package com.example.demo.repository.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.dto.UsuarioDTO;
import com.example.demo.repository.entity.Usuario;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Repository
@Transactional
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Usuario findByCorreo(String correo);
	
	@Query(value = "SELECT DISTINCT u FROM Usuario u JOIN u.listaRoles r WHERE r.id IN (5, 6)")
	List<Usuario> findAllTrabajadores();

	@Query(value = "SELECT u FROM Usuario u WHERE u.correo = :correo AND u.password = :contrasenya")
	List<Usuario> findByEmailAndPassword(@Param("correo")String correo, @Param("contrasenya")String password);

	@Query(value = "SELECT u FROM Usuario u WHERE u.correo LIKE %:correo%")
	List<Usuario> findAllByTerm(@Param("correo")String searchTerm);

	@Query(value = "SELECT DISTINCT u FROM Usuario u JOIN u.listaRoles r WHERE r.id IN (5, 6) AND u.correo LIKE %:correo%")
	List<Usuario> findAllTrabajadoresByTerm(@Param("correo")String searchTerm);

}
