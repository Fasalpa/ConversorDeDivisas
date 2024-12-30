package com.exchange.consultas;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Consultas {

	private String key = "a189be74e45ff6138f8fc080";

	public Moneda convertirMonedas() throws InterruptedException {

		URI direccion =  URI.create("https://v6.exchangerate-api.com/v6/" +
				key + "latest/usd");

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(direccion)
				.build();

		try {

			HttpResponse<String> response = client
					.send(request, HttpResponse.BodyHandlers.ofString());

			if (response.statusCode() == 200){
				return new Gson().fromJson(response.body(), Moneda.class);
			}else {
				System.out.println("Error en la solicitud" + response.statusCode());
				return null;
			}

		} catch (IOException | InterruptedException e) {
			System.out.println("Error al conectarse a la API: " + e.getMessage());
			return null;
		}
	}
}
