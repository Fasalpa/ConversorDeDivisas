package com.exchange.errores;

import com.exchange.consultas.Moneda;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class GeneradorDeArchivoDeErrores {

	public void guardarJson(Moneda moneda) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		String nombreArchivo = "conversiones-" + System.currentTimeMillis() + ".json";
		//Escritura para convertir el Gson en json y que se agregue (falta completar)
		try (FileWriter escritura = new FileWriter(nombreArchivo)) {
			escritura.write(gson.toJson(moneda));
		}
	}

	public void guardarJson(String contenido, String nombreArchivo) throws IOException {
		// Guarda un contenido genérico cuando no se tiene el objeto Moneda
		try (FileWriter escritura = new FileWriter(nombreArchivo)) {
			escritura.write(contenido);
		}

	}
}
