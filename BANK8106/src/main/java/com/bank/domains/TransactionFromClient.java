package com.bank.domains;

public class TransactionFromClient {
	private String comprador;
	private String vendedor;
	private Long coste;
	private String fechaTarjeta;
	private String fechaTransaccion;
	int cv2;
	Long tarjeta;
	
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
	public Long getCoste() {
		return coste;
	}
	public void setCoste(Long coste) {
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
}
