package org.ciberfarma.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity 
@Table(name = "tb_productos")
@NamedQuery(name = "FindAll", query = "select p from Producto p") 



public class Producto { 

	
	
	@Id 
	@Column(name = "id_prod")
	private String idprod;  
	
	@Column(name="des_prod") 
	private String descripcion; 
	
	@Column(name = "stk_prod") 
	private int stock; 
	
	@Column(name = "pre_prod") 
	private double precio;  
	
	@Column(name = "idcategoria") 
	private int idcategoria;  
	
	@Column(name = "est_prod") 
	private int  estado; 
	
	@Column(name = "idproveedor") 
	private int idproveedor;

	public String getIdprod() {
		return idprod;
	}

	public void setIdprod(String idprod) {
		this.idprod = idprod;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getIdcategoria() {
		return idcategoria;
	}

	public void setIdcategoria(int idcategoria) {
		this.idcategoria = idcategoria;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getIdproveedor() {
		return idproveedor;
	}

	public void setIdproveedor(int idproveedor) {
		this.idproveedor = idproveedor;
	} 
	
	
	

}
