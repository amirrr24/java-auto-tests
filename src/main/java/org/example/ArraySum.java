package org.example;

public class ArraySum {

    public static int sum(String[][] array) throws MyArraySizeException, MyArrayDataException {
        // 1. Проверяем размер массива (должен быть 4×4)
        if (array.length != 4) {
            throw new MyArraySizeException("Ожидался массив 4×4, получено " + array.length + " строк");
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i].length != 4) {
                throw new MyArraySizeException("В строке " + i + " ожидалось 4 элемента, получено " + array[i].length);
            }
        }

        int sum = 0;

        // 2. Проходим по всем элементам и суммируем
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    // Если не число — бросаем своё исключение с координатами
                    throw new MyArrayDataException("В ячейке [" + i + "][" + j + "] лежит '" + array[i][j] + "' — это не число");
                }
            }
        }

        return sum;
    }
}