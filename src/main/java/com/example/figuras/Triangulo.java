package com.example.figuras;

public class Triangulo implements Figura {

    private final double lado1;
    private final double lado2;
    private final double lado3;

    public Triangulo(double lado1, double lado2, double lado3) {
        if (!Double.isFinite(lado1)
                || !Double.isFinite(lado2)
                || !Double.isFinite(lado3)
                || lado1 <= 0 || lado2 <= 0 || lado3 <= 0) {
            throw new IllegalArgumentException(
                    "Los lados deben ser números positivos."
            );
        }

        if (lado1 + lado2 <= lado3
                || lado1 + lado3 <= lado2
                || lado2 + lado3 <= lado1) {
            throw new IllegalArgumentException(
                    "Estos lados no forman un triángulo."
            );
        }

        this.lado1 = lado1;
        this.lado2 = lado2;
        this.lado3 = lado3;
    }

    @Override
    public double calcularArea() {
        double s = calcularPerimetro() / 2;

        return Math.sqrt(
                s * (s - lado1) * (s - lado2) * (s - lado3)
        );
    }

    @Override
    public double calcularPerimetro() {
        return lado1 + lado2 + lado3;
    }
}
