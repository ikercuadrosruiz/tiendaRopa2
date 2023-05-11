package com.example.demo.repository.entity;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;
	
@Data
@Entity
@Table(name = "imagenes")
public class Imagen {
	
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Long id;
		
		@Column(name = "nombre")
		private String nombre;
		
		@Column(name = "url")
		private String url;
		
		@ManyToMany(mappedBy = "listaImagenes")
		@ToString.Exclude
		private Set<Producto> listaProductos;

		// Equals --------------------------------------
		
		@Override
		public int hashCode() {
			return Objects.hash(id);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Imagen other = (Imagen) obj;
			return Objects.equals(id, other.id);
		}
		
		
}