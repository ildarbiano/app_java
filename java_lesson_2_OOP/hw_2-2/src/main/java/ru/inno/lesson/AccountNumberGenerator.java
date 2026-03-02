package ru.inno.lesson;

/**
 * Генератор номеров счетов (п.1)
 */
public class AccountNumberGenerator {
    private static int counter = 0;

    public static int getNext() {
        return ++counter;
    }

    public static int getCurrent() {
        return counter;
    }

    public void reset() {
        counter = 0;
    }
}
