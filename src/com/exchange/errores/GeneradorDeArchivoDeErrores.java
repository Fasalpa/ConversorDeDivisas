package com.exchange.errores;

import com.exchange.consultas.Moneda;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;

public class GeneradorDeArchivoDeErrores {

	public void guardarJson(Moneda moneda) throws IOException{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		//Escritura para convertir el Gson en json y que se agregue (falta completar)
		try (FileWriter escritura = new FileWriter(moneda.base_code() + "-error.json")) {
			escritura.write(gson.toJson(moneda));
		}
	}
	public void guardarJson(String contenido, String nombreArchivo) throws IOException {
		// Guarda un contenido genérico cuando no se tiene el objeto Moneda
		try (FileWriter escritura = new FileWriter(nombreArchivo + "-error.json")) {
			escritura.write(contenido);
		}

	}
}
