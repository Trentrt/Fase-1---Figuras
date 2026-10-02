package com.example.figuras;

public class Cuadrado implements Figura {

    private final double lado;

    public Cuadrado(double lado) {
        if (!Double.isFinite(lado) || lado <= 0) {
            throw new IllegalArgumentException(
                    "El lado debe ser un número positivo."
            );
        }

        this.lado = lado;
    }

    @Override
    public double calcularArea() {
        return lado * lado;
    }

    @Override
    public double calcularPerimetro() {
        return 4 * lado;
    }
}
