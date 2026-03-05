package ru.collection.arr3;

import java.util.ArrayList;

public class ReverseArrayList {
    public static void main(String[] args) {
        int N = 10; // N должно быть чётным (для соответствия условию)

        // Создаём список и заполняем числами от 1 до N
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            list.add(i);
        }

        System.out.println("Исходный список: " + list);

        // Вызов метода для реверса списка
        reverseArrayList(list);

        System.out.println("Список после реверса: " + list);
    }

    /**
     * Метод переставляет элементы ArrayList<Integer> в обратном порядке.
     * @param list список, который нужно развернуть
     */
    public static void reverseArrayList(ArrayList<Integer> list) {
        int left = 0;
        int right = list.size() - 1;

        while (left < right) {
            // Обмен элементов
            int temp = list.get(left);
            list.set(left, list.get(right));
            list.set(right, temp);

            left++;
            right--;
        }
    }
}