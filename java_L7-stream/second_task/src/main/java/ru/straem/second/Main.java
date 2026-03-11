package ru.straem.second;

import java.util.ArrayList;

public class Main {

    public static void printList(ArrayList<Integer> list) {
        // Используем метод forEach с лямбда-выражением для вывода каждого элемента
        list.forEach(x -> System.out.println(x));
    }

    public static void main(String[] args) {
        // Создаем и заполняем список
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        numbers.add(40);
        numbers.add(50);

        // Вызываем метод для вывода списка
        printList(numbers);
    }
}