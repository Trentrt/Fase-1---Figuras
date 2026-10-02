package com.example.figuras;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class FigurasApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(FigurasApplication.class, args);
    }

    @Override
    public void run(String... args) {
        FiguraFactory factory = new FiguraFactory();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== CALCULADORA DE FIGURAS ===");
            System.out.println("1. Calcular área y perímetro");
            System.out.println("0. Salir");
            System.out.print("Selecciona una opción: ");

            if (!scanner.hasNextLine()) {
                break;
            }

            String opcion = scanner.nextLine().trim();

            if (opcion.equals("0")) {
                System.out.println("Programa finalizado.");
                break;
            }

            if (!opcion.equals("1")) {
                System.out.println("Opción incorrecta.");
                continue;
            }

            try {
                System.out.println("\nFiguras disponibles:");
                System.out.println("Circulo: radio");
                System.out.println("Cuadrado: lado");
                System.out.println("Triangulo: lado1, lado2, lado3");

                System.out.print("\nNombre de la figura: ");
                String nombre = scanner.nextLine().trim();

                System.out.print(
                        "Escribe las medidas separadas por espacios: "
                );

                String entrada = scanner.nextLine().trim();

                if (entrada.isEmpty()) {
                    throw new IllegalArgumentException(
                            "Debes ingresar las medidas."
                    );
                }

                String[] valores = entrada.split("\\s+");
                double[] parametros = new double[valores.length];

                for (int i = 0; i < valores.length; i++) {
                    parametros[i] = Double.parseDouble(
                            valores[i].replace(',', '.')
                    );
                }

                Figura figura = factory.crearFigura(nombre, parametros);

                System.out.println("\nFigura: " + nombre);
                System.out.printf(
                        "Área: %.2f%n", figura.calcularArea()
                );
                System.out.printf(
                        "Perímetro: %.2f%n", figura.calcularPerimetro()
                );

            } catch (NumberFormatException e) {
                System.out.println(
                        "Error: ingresa únicamente medidas numéricas."
                );
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}