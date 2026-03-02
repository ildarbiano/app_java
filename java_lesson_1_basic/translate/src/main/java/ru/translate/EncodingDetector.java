package ru.translate;

import java.io.*;
import java.nio.charset.*;
import java.nio.charset.StandardCharsets;

public class EncodingDetector {

    // Список возможных кодировок для русского текста в Windows
    private static final Charset[] POSSIBLE_CHARSETS = {
            StandardCharsets.UTF_8,
            Charset.forName("Windows-1251"),  // Стандартная для русской Windows
            Charset.forName("KOI8-R"),        // Старая русская кодировка
            Charset.forName("ISO-8859-5"),    // Latin/Cyrillic
            Charset.forName("CP866")          // DOS/OEM кодировка
    };

    /**
     * Определяет кодировку текста
     */
    public static Charset detectEncoding(byte[] bytes) {
        // Сначала пробуем UTF-8 (самый распространенный)
        if (isValidUTF8(bytes)) {
            return StandardCharsets.UTF_8;
        }

        // Пробуем другие кодировки
        for (Charset charset : POSSIBLE_CHARSETS) {
            if (charset.equals(StandardCharsets.UTF_8)) {
                continue; // UTF-8 уже проверили
            }

            try {
                String text = new String(bytes, charset);
                if (containsRussianText(text)) {
                    return charset;
                }
            } catch (Exception e) {
                // Пропускаем эту кодировку
            }
        }

        // Если не удалось определить, возвращаем кодировку по умолчанию для Windows
        return Charset.forName("Windows-1251");
    }

    /**
     * Проверяет, является ли байтовый массив валидным UTF-8
     */
    private static boolean isValidUTF8(byte[] bytes) {
        try {
            String text = new String(bytes, StandardCharsets.UTF_8);
            // Дополнительная проверка: текст должен содержать хотя бы некоторые осмысленные символы
            return !text.contains("�"); // Заменяем на проверку без replacement character
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Проверяет, содержит ли текст кириллические символы
     */
    private static boolean containsRussianText(String text) {
        if (text == null || text.isEmpty()) {
            return false;
        }

        // Считаем количество русских букв
        int russianLetters = 0;
        int totalLetters = 0;

        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                totalLetters++;
                // Проверяем, является ли символ кириллическим
                if ((c >= 'А' && c <= 'я') || c == 'Ё' || c == 'ё') {
                    russianLetters++;
                }
            }
        }

        // Если в тексте есть буквы и хотя бы 30% из них - русские
        return totalLetters > 0 && (russianLetters * 100 / totalLetters) >= 30;
    }

    /**
     * Получает кодировку по умолчанию для текущей системы
     */
    public static Charset getDefaultSystemCharset() {
        String osName = System.getProperty("os.name", "").toLowerCase();

        if (osName.contains("win")) {
            // Для Windows обычно используется Windows-1251
            return Charset.forName("Windows-1251");
        } else {
            // Для других систем - UTF-8
            return StandardCharsets.UTF_8;
        }
    }
}