package com.catalogue.checks;

public class ValidacionCategoria {

	public boolean validarCategoria(String categoria) {

		switch (categoria) {
		case "Motor":
		case "Moda":
		case "Telefonía":
		case "Informática y Electrónica":
		case "Electrodomésticos":
		case "Deporte y Ocio":
		case "Hogar y Jardín":
		case "Cine, Libros y Música":
		case "TV, Audio y Foto":
			return true;
		default:
			return false;
		}

	}

}
