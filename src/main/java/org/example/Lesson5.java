package org.example;

public class Lesson5 {
    public static void main(String[] args) {
        // Тест 1: правильный массив
        String[][] correctArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        // Тест 2: неправильный размер (3 строки)
        String[][] wrongSizeArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"}
        };

        // Тест 3: неправильный размер (не 4 столбца)
        String[][] wrongColumnArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15"}
        };

        // Тест 4: нечисловые данные
        String[][] wrongDataArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "A", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        System.out.println("=== ТЕСТ 1: ПРАВИЛЬНЫЙ МАССИВ ===");
        try {
            int result = ArraySum.sum(correctArray);
            System.out.println("Сумма элементов: " + result);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        System.out.println("\n=== ТЕСТ 2: НЕПРАВИЛЬНОЕ КОЛИЧЕСТВО СТРОК ===");
        try {
            int result = ArraySum.sum(wrongSizeArray);
            System.out.println("Сумма элементов: " + result);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        System.out.println("\n=== ТЕСТ 3: НЕПРАВИЛЬНОЕ КОЛИЧЕСТВО СТОЛБЦОВ ===");
        try {
            int result = ArraySum.sum(wrongColumnArray);
            System.out.println("Сумма элементов: " + result);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        System.out.println("\n=== ТЕСТ 4: НЕЧИСЛОВЫЕ ДАННЫЕ ===");
        try {
            int result = ArraySum.sum(wrongDataArray);
            System.out.println("Сумма элементов: " + result);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        // Дополнительное задание: ArrayIndexOutOfBoundsException
        System.out.println("\n=== ДОП. ЗАДАНИЕ: ArrayIndexOutOfBoundsException ===");
        try {
            int[] arr = {1, 2, 3};
            System.out.println(arr[5]); // выход за границы массива
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Поймано исключение: " + e);
            System.out.println("Сообщение: " + e.getMessage());
        }
    }
}