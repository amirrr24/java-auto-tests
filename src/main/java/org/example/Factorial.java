package org.example;

public class Factorial {
    public static long calculate(int n) {
        if (n < 0) throw new IllegalArgumentException("Факториал отрицательного числа не определён");
        if (n == 0 || n == 1) return 1;
        long result = 1;
        for (int i = 2; i <= n; i++) result *= i;
        return result;
    }
}