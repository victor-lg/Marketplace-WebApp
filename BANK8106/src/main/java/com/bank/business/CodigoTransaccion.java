package com.bank.business;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CodigoTransaccion {

	public CodigoTransaccion() {
		// TODO Auto-generated constructor stub
	}

	private String generacionCodigo() {

		Date _fechaActual = new Date();

		SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddhhmmss");

		return ft.format(_fechaActual);

	}

	public String generacionCodigoTransaccion(String fechaTarjeta) {

		return "TRANSACION" + generacionCodigo() + fechaTarjeta;

	}

}
