package com.exchange;

import com.exchange.consultas.Consultas;
import com.exchange.consultas.Moneda;
import com.exchange.errores.GeneradorDeArchivoDeErrores;

import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);
		Consultas consultas = new Consultas();
		GeneradorDeArchivoDeErrores generadorErrores = new GeneradorDeArchivoDeErrores();

		String separador = "***************************";
		String opciones = """
				Seleccione una de las siguientes opciones:
				1) USD ==> COP.
				2) COP ==> USD.
				3) JPY ==> COP.
				4) COP ==> JPY
				5) KRW ==> COP.
				6) COP ==> KRW
				0) Salir.
				""";
		double monto;
		int numeroSeleccionado = 0;


		while (true) {
			System.out.println(opciones);
			numeroSeleccionado = teclado.nextInt();


			switch (numeroSeleccionado) {
				case 1, 2, 3, 4, 5, 6 -> {
					System.out.println(separador + "\nDigíte el monto que desea convertir:");
					monto = teclado.nextInt();

					try {
						Moneda moneda = consultas.convertirMonedas();

						if (moneda != null) {

							System.out.println("Datos obtenidos con éxito");
							String claveOrigen = "";
							String claveDestino = "";

							switch (numeroSeleccionado) {
								case 1 -> {
									claveOrigen = "USD";
									claveDestino = "COP";
								}
								case 2 -> {
									claveOrigen = "COP";
									claveDestino = "USD";
								}
								case 3 -> {
									claveOrigen = "JPY";
									claveDestino = "COP";
								}
								case 4 -> {
									claveOrigen = "COP";
									claveDestino = "JPY";
								}
								case 5 -> {
									claveOrigen = "KRW";
									claveDestino = "COP";
								}
								case 6 -> {
									claveOrigen = "COP";
									claveDestino = "KRW";
								}
							}
							if (moneda.conversion_rates().containsKey(claveOrigen) && moneda.conversion_rates().containsKey(claveDestino)) {

								double tasaOrigen = moneda.conversion_rates().get(claveOrigen);
								double tasaDestino = moneda.conversion_rates().get(claveDestino);

								double tasaConversion = tasaDestino / tasaOrigen;
								double resultado = monto * tasaConversion;

								System.out.printf("La conversión de %.2f %s a %s es: %.2f %s\n",
										monto, claveOrigen, claveDestino, resultado, claveDestino);

//es para guardar la respuesta
								generadorErrores.guardarJson(moneda);
							} else {
								System.out.println("No se encontraron tasas de conversión para las monedas seleccionadas.");
							}
						} else {
							System.out.println("No se pudo procesar la petición.");
						}
					} catch (IOException e) {
						System.out.println("Error al guardar el archivo: " + e.getMessage());
					} catch (InterruptedException e) {
						System.out.println("Error en la conexión: " + e.getMessage());
					}
				}
				case 0 -> {
					System.out.println("Terminando el proceso, gracias por usar nuestro servicio.");
					return;
				}
				default -> System.out.println("Digíte un número válido.");
			}
		}
	}
}
