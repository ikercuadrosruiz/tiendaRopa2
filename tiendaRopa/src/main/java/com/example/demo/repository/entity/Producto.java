/*
package com.example.demo.repository.entity;

import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "productos")
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "numeroproducto")
	private String numeroProducto;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "stock")
	private int stock;
	
	@Column(name = "precio")
	private Float precio;
	
	@Column(name = "talla")
	private String talla;
	
	@Column(name = "color")
	private String color;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name = "idcategoria")
	@ToString.Exclude
	private Categoria categoria;
	
	@OneToMany(
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL,
			mappedBy = "producto")
	@ToString.Exclude
	private Set<CarritoProducto> listaCarritoProducto;
	
	@OneToMany(
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL,
			mappedBy = "producto")
	@ToString.Exclude
	private Set<PedidoProducto> listaPedidoProducto;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinTable(
			name = "imagenproducto",//Nombre de la tabla
			joinColumns = @JoinColumn(name = "idproducto"),
			inverseJoinColumns = @JoinColumn(name = "iddireccion")
	)
	@ToString.Exclude
	private Set<Imagen> listaImagenes;

	
	// Equals -------------------------------------------------
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
*/