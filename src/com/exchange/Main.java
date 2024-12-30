package com.exchange;

import com.exchange.consultas.Moneda;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);


		String separador = "***************************";
		String opciones = """
				Seleccione una de las siguientes opciones:
				1) USD ==> COP.
				2) COP ==> USD.
				3) JPY ==> COP.
				4) KRW ==> COP.
				0) Salir.
				""";
		int numeroSeleccionado = 0;
		double conversion;

		while (true) {
			System.out.println(opciones);
			numeroSeleccionado = teclado.nextInt();

			switch (numeroSeleccionado) {
				case 1, 2, 3, 4:
					System.out.println(separador + "\nDigíte el monto que desea convertir:");
					conversion = teclado.nextInt();
					break;
				case 0:
					System.out.println("Terminando el proceso, gracias por usar nuestro servicio.");
					return;
				default:
					System.out.println("Digíte un número válido.");
			}
		}
	}
}