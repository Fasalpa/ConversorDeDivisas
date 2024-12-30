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

		String separador = "***************************\n";
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
			System.out.println(separador + opciones);
			System.out.print("Ingrese su opción: ");

			if (teclado.hasNextInt()) {
				numeroSeleccionado = teclado.nextInt();
				teclado.nextLine();  // Consumir el salto de línea para evitar problemas con entradas

			} else {
				System.out.println(separador + "Ingrese un número válido.");
				teclado.nextLine();  // Consumir la entrada no válida
				continue;
			}

			switch (numeroSeleccionado) {
				case 1, 2, 3, 4, 5, 6 -> {
					System.out.println(separador + "\nDigíte el monto que desea convertir:");
					if (teclado.hasNextDouble()) {
						monto = teclado.nextDouble();
						teclado.nextLine();  // Consumir el salto de línea

						try {
							Moneda moneda = consultas.convertirMonedas();

							if (moneda != null) {
								System.out.println(separador + "Datos obtenidos con éxito");
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

									System.out.printf("La conversión de %.2f [%s] a [%s] es: %.2f [%s]\n",
											monto, claveOrigen, claveDestino, resultado, claveDestino);

									// Guardar la respuesta en un archivo
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
							throw new RuntimeException(e);
						}
					} else {
						System.out.println(separador + "Ingrese un monto válido.");
						teclado.nextLine();  // Consumir la entrada no válida
					}
				}
				case 0 -> {
					System.out.println("Terminando el proceso, gracias por usar nuestro servicio.");
					teclado.close();
					return;
				}
				default -> System.out.println(separador + "Por favor, seleccione una opción válida.");
			}
		}
	}
}
