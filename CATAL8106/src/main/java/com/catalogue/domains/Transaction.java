package com.catalogue.domains;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name="transactions")
@NamedQuery(name="Transaction.findAll", query="SELECT t FROM Transaction t")
public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private float coste;

	private int cv2;

	private String fechaTarjeta;

	private BigInteger tarjeta;

	@ManyToOne
	@JoinColumn(name="cod_pedido")
	private Item item;

	public Transaction() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}