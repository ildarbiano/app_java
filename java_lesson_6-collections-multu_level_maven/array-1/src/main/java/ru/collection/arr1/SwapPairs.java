package ru.collection.arr1;

import java.util.ArrayList;
import java.util.List;

public class SwapPairs {
    public static void main(String[] args) {
        // Задаём чётное N
        int N = 10;

        // Создаём список и заполняем числами от 1 до N
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            list.add(i);
        }

        System.out.println("Исходный список: " + list);

        // Попарно меняем элементы: 0<->1, 2<->3, 4<->5, ...
        for (int i = 0; i < list.size(); i += 2) {
            // Меняем местами i и i+1
            int temp = list.get(i);
            list.set(i, list.get(i + 1));
            list.set(i + 1, temp);
        }

        System.out.println("Список после обмена: " + list);
    }
}