package ru.stepup.payments.mobile_pay;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MainApplication {

    public static void main(String[] args) {

        InputStream is = MainApplication.class
                .getClassLoader()
                .getResourceAsStream("resources.info.txt");

        if (is == null) {
            System.out.println("Файл не найден!");
            return;
        }

        try (BufferedReader reader =
                     new BufferedReader(
                             new InputStreamReader(is, StandardCharsets.UTF_8))) {

            reader.lines().forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
