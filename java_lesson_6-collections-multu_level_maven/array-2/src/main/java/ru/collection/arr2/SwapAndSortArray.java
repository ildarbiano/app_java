package ru.collection.arr2;

import java.util.Arrays;

public class SwapAndSortArray {
    public static void main(String[] args) {
        int N = 10; // N должно быть чётным

        // 1. Создаём массив и заполняем числами от 1 до N
        int[] intArray = new int[N];
        for (int i = 0; i < N; i++) {
            intArray[i] = i + 1;
        }

        System.out.println("Исходный массив:        " + Arrays.toString(intArray));

        // 2. Попарно меняем элементы: 0↔1, 2↔3, 4↔5, ...
        for (int i = 0; i < intArray.length; i += 2) {
            int temp = intArray[i];
            intArray[i] = intArray[i + 1];
            intArray[i + 1] = temp;
        }

        System.out.println("После попарного обмена: " + Arrays.toString(intArray));

        // 3. Сортируем массив по возрастанию
        Arrays.sort(intArray);
        System.out.println("После сортировки:       " + Arrays.toString(intArray));
    }
}