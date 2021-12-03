package com.bank.business;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ValidacionTarjeta {

	public boolean validarTarjeta(String fechaTarjeta, int cv2, Long numeroTarjeta) {

		DateFormat objSDF = new SimpleDateFormat("yyyyMMdd");

		Date _fechaActual = new Date();

		int tam = (Long.toString(numeroTarjeta).length());
		int mod = (int) (numeroTarjeta % 3);
		int tamCV2 = (Integer.toString(cv2).length());

		return ((objSDF.format(_fechaActual).compareTo(fechaTarjeta) < 0) && tam == 16 && mod == 0
				&& tamCV2 == 3);

	}

}