package com.bank.domains;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the transactions database table.
 * 
 */
@Entity
@Table(name="transactions")
@NamedQuery(name="Transaction.findAll", query="SELECT t FROM Transaction t")
public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="cod_pedido")
	private int codPedido;

	private float coste;

	private int cv2;

	private String fechaTarjeta;

	private BigInteger tarjeta;

	public Transaction() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getCodPedido() {
		return this.codPedido;
	}

	public void setCodPedido(int codPedido) {
		this.codPedido = codPedido;
	}

	public float getCoste() {
		return this.coste;
	}

	public void setCoste(float coste) {
		this.coste = coste;
	}

	public int getCv2() {
		return this.cv2;
	}

	public void setCv2(int cv2) {
		this.cv2 = cv2;
	}

	public String getFechaTarjeta() {
		return this.fechaTarjeta;
	}

	public void setFechaTarjeta(String fechaTarjeta) {
		this.fechaTarjeta = fechaTarjeta;
	}

	public BigInteger getTarjeta() {
		return this.tarjeta;
	}

	public void setTarjeta(BigInteger tarjeta) {
		this.tarjeta = tarjeta;
	}

}