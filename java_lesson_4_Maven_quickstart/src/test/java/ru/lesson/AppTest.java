package ru.lesson;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AppTest {
    @Test //аннотация, помечает метод как тестовый метод,
    // метод, который должен выполняться при запуске тестов.
    public void test() {
        Assertions.assertEquals(  1, 1 );
    }
}

