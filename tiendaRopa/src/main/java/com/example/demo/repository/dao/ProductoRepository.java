package com.example.demo.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.repository.entity.Producto;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
