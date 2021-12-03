package com.bank.domains;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Document(collection = "transactions")
@JsonPropertyOrder({ "id", "codPedido", "comprador", "vendedor", "coste", "fechaTransaccion","fechaTarjeta" ,"cv2","tarjeta"})
public class BankTransaction {
	@Id
	private String id;
	private String codPedido;
	private String comprador;
	private String vendedor;
	private Float coste;
	private String fechaTarjeta;
	private String fechaTransaccion;
	int cv2;
	Long tarjeta;
	
	@PersistenceConstructor
	public BankTransaction(String id, String codPedido, String comprador, String vendedor, Float coste,
			String fechaTarjeta, String fechaTransaccion, int cv2, Long tarjeta) {
		
		this.id = id;
		this.codPedido = codPedido;
		this.comprador = comprador;
		this.vendedor = vendedor;
		this.coste = coste;
		this.fechaTarjeta = fechaTarjeta;
		this.fechaTransaccion = fechaTransaccion;
		this.cv2 = cv2;
		this.tarjeta = tarjeta;
	}
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCodPedido() {
		return codPedido;
	}

	public void setCodPedido(String codPedido) {
		this.codPedido = codPedido;
	}

	public String getComprador() {
		return comprador;
	}

	public void setComprador(String comprador) {
		this.comprador = comprador;
	}

	public String getVendedor() {
		return vendedor;
	}

	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}

	public Float getCoste() {
		return coste;
	}

	public void setCoste(Float coste) {
		this.coste = coste;
	}

	public String getFechaTarjeta() {
		return fechaTarjeta;
	}

	public void setFechaTarjeta(String fechaTarjeta) {
		this.fechaTarjeta = fechaTarjeta;
	}

	public String getFechaTransaccion() {
		return fechaTransaccion;
	}

	public void setFechaTransaccion(String fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	public int getCv2() {
		return cv2;
	}

	public void setCv2(int cv2) {
		this.cv2 = cv2;
	}

	public Long getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(Long tarjeta) {
		this.tarjeta = tarjeta;
	}

	public BankTransaction() {

	}

}
