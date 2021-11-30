package com.catalogue.model;

import java.io.Serializable;
import javax.persistence.*;

import com.catalogue.model.Item;

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
	private String idtransactions;

	private BigInteger coste;

	private int cv2;

	@Column(name="fecha_tarjeta")
	private String fechaTarjeta;

	@Column(name="num_tarjeta")
	private BigInteger numTarjeta;

	//bi-directional many-to-one association to Item
	@ManyToOne
	@JoinColumn(name="sold_item")
	private Item item;

	public Transaction() {
	}

	public String getIdtransactions() {
		return this.idtransactions;
	}

	public void setIdtransactions(String idtransactions) {
		this.idtransactions = idtransactions;
	}

	public BigInteger getCoste() {
		return this.coste;
	}

	public void setCoste(BigInteger coste) {
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

	public BigInteger getNumTarjeta() {
		return this.numTarjeta;
	}

	public void setNumTarjeta(BigInteger numTarjeta) {
		this.numTarjeta = numTarjeta;
	}

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}