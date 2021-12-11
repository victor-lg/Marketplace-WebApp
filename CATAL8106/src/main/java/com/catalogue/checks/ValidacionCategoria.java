package com.catalogue.checks;

public class ValidacionCategoria {

	public boolean validarCategoria(String categoria) {

		switch (categoria) {
		case "Motor":
		case "Moda":
		case "Telefonia":
		case "Informatica y Electronica":
		case "Electrodomesticos":
		case "Deporte y Ocio":
		case "Hogar y Jardin":
		case "Cine, Libros y Musica":
		case "TV, Audio y Foto":
			return true;
		default:
			return false;
		}

	}

}
