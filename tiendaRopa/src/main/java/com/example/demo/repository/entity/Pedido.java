package com.example.demo.repository.entity;

import java.util.Date;
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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "pedidos")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "numerofactura")
	private String numeroFactura;
	
	@Column(name = "precio")
	private Float precio;
	
	@Column(name = "estado")
	private String estado;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fechaemision")
	private Date fechaEmision;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fechaentrega")
	private Date fechaEntrega;
	
	@ManyToOne
	@JoinColumn(name = "idcliente")
	@ToString.Exclude
	private Usuario usuario;
	
	/*
	@OneToMany(
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL,
			mappedBy = "pedido")
	@ToString.Exclude
	private Set<PedidoProducto> listaPedidoProducto;
	*/
	
	// Equals ---------------------------------------
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
}