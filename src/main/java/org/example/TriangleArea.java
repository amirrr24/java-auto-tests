package org.example;

public class TriangleArea {
    public static double calculate(double a, double b, double c) {
        if (a <= 0 || b <= 0 || c <= 0)
            throw new IllegalArgumentException("Стороны должны быть положительными");
        if (a + b <= c || a + c <= b || b + c <= a)
            throw new IllegalArgumentException("Треугольник не существует");
        double p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }
}