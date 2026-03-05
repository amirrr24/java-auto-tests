package org.example;

public class Lesson2Tasks {

    public static void main(String[] args) {
        System.out.println("=== Задание 1 ===");
        printThreeWords();

        System.out.println("\n=== Задание 2 ===");
        checkSumSign();

        System.out.println("\n=== Задание 3 ===");
        printColor();

        System.out.println("\n=== Задание 4 ===");
        compareNumbers();

        System.out.println("\n=== Задание 5 ===");
        System.out.println("10 и 5 → " + checkSumInRange(10, 5));
        System.out.println("15 и 2 → " + checkSumInRange(15, 2));
        System.out.println("5 и 3 → " + checkSumInRange(5, 3));

        System.out.println("\n=== Задание 6 ===");
        checkPositive(5);
        checkPositive(-3);
        checkPositive(0);

        System.out.println("\n=== Задание 7 ===");
        System.out.println("-3 → " + isNegative(-3));
        System.out.println("5 → " + isNegative(5));
        System.out.println("0 → " + isNegative(0));

        System.out.println("\n=== Задание 8 ===");
        repeatString("Java", 3);

        System.out.println("\n=== Задание 9 ===");
        System.out.println("2020 → " + isLeapYear(2020));
        System.out.println("2021 → " + isLeapYear(2021));
        System.out.println("1900 → " + isLeapYear(1900));
        System.out.println("2000 → " + isLeapYear(2000));

        System.out.println("\n=== Задание 10 ===");
        changeArray();

        System.out.println("\n=== Задание 11 ===");
        fillArray();

        System.out.println("\n=== Задание 12 ===");
        multiplyArray();

        System.out.println("\n=== Задание 13 ===");
        fillDiagonal();

        System.out.println("\n=== Задание 14 ===");
        int[] resultArray = createArray(5, 7);
        for (int num : resultArray) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // 1. Печатает три слова в столбик
    public static void printThreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }

    // 2. Проверяет сумму двух чисел
    public static void checkSumSign() {
        int a = 5;
        int b = -3;
        int sum = a + b;
        if (sum >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

    // 3. Определяет цвет по числу
    public static void printColor() {
        int value = 50;
        if (value <= 0) {
            System.out.println("Красный");
        } else if (value <= 100) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }
    }

    // 4. Сравнивает два числа
    public static void compareNumbers() {
        int a = 7;
        int b = 5;
        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }

    // 5. Проверяет, лежит ли сумма в диапазоне [10, 20]
    public static boolean checkSumInRange(int a, int b) {
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }

    // 6. Печатает, положительное ли число (0 считаем положительным)
    public static void checkPositive(int num) {
        if (num >= 0) {
            System.out.println(num + " — положительное");
        } else {
            System.out.println(num + " — отрицательное");
        }
    }

    // 7. Возвращает true, если число отрицательное
    public static boolean isNegative(int num) {
        return num < 0;
    }

    // 8. Печатает строку N раз
    public static void repeatString(String str, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println(str);
        }
    }

    // 9. Проверяет, является ли год високосным
    public static boolean isLeapYear(int year) {
        if (year % 400 == 0) {
            return true;
        }
        if (year % 100 == 0) {
            return false;
        }
        return year % 4 == 0;
    }

    // 10. Меняет 0 на 1, 1 на 0 в массиве
    public static void changeArray() {
        int[] arr = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (arr[i] == 0) ? 1 : 0;
        }
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // 11. Заполняет массив числами от 1 до 100
    public static void fillArray() {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        for (int i = 0; i < 20; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("...");
    }

    // 12. Умножает на 2 числа, меньшие 6
    public static void multiplyArray() {
        int[] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) {
                arr[i] *= 2;
            }
        }
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // 13. Заполняет диагональ двумерного массива единицами
    public static void fillDiagonal() {
        int size = 5;
        int[][] arr = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                arr[i][j] = 0;
            }
        }
        for (int i = 0; i < size; i++) {
            arr[i][i] = 1;
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    // 14. Создаёт массив длины len, заполненный initialValue
    public static int[] createArray(int len, int initialValue) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = initialValue;
        }
        return arr;
    }
}