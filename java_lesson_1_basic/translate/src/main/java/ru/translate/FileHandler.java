package ru.translate;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.time.*;
import java.time.format.*;

public class FileHandler {

    /**
     * Читает текстовый файл с автоматическим определением кодировки
     */
    public static String readFile(String filePath) throws IOException {
        // Читаем файл как байты
        byte[] bytes = Files.readAllBytes(Paths.get(filePath));

        // Определяем кодировку
        Charset charset = EncodingDetector.detectEncoding(bytes);
        System.out.println("Определена кодировка файла: " + charset.name());

        // Преобразуем байты в строку с правильной кодировкой
        return new String(bytes, charset);
    }

    /**
     * Записывает результаты анализа в файл
     */
    public static void writeResults(String outputPath, AnalysisResult result) throws IOException {
        String output = formatResults(result);

        // Используем UTF-8 для записи результатов (современный стандарт)
        Files.write(
                Paths.get(outputPath),
                output.getBytes("UTF-8"),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
        );
    }

    /**
     * Форматирует результаты анализа для вывода
     */
    private static String formatResults(AnalysisResult result) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        String timestamp = LocalDateTime.now().format(formatter);

        StringBuilder output = new StringBuilder();
        output.append("=".repeat(70)).append("\n");
        output.append("АНАЛИЗ ТЕКСТА - ").append(timestamp).append("\n");
        output.append("=".repeat(70)).append("\n\n");

        // Основная статистика
        output.append("📊 ОСНОВНАЯ СТАТИСТИКА:\n");
        output.append("─".repeat(40)).append("\n");
        output.append("• Количество предложений: ").append(result.getSentenceCount()).append("\n");
        output.append("• Количество слов: ").append(result.getWordCount()).append("\n\n");

        // Самые длинные предложения
        output.append("📝 САМЫЕ ДЛИННЫЕ ПРЕДЛОЖЕНИЯ");
        if (result.getLongestSentences().size() > 1) {
            output.append(" (").append(result.getLongestSentences().size()).append(" шт.):\n");
        } else {
            output.append(":\n");
        }
        output.append("─".repeat(40)).append("\n");

        int counter = 1;
        for (String sentence : result.getLongestSentences()) {
            output.append(counter++).append(". ").append(sentence).append("\n");
            output.append("   Длина: ").append(sentence.length()).append(" символов\n\n");
        }

        // Самое длинное слово
        if (result.getLongestWord() != null && !result.getLongestWord().isEmpty()) {
            output.append("🔤 САМОЕ ДЛИННОЕ СЛОВО:\n");
            output.append("─".repeat(40)).append("\n");
            output.append("• Слово: \"").append(result.getLongestWord()).append("\"\n");
            output.append("• Длина: ").append(result.getLongestWord().length()).append(" символов\n\n");

            if (result.getSentenceWithLongestWord() != null) {
                output.append("📄 ПРЕДЛОЖЕНИЕ С ЭТИМ СЛОВОМ:\n");
                output.append("─".repeat(40)).append("\n");
                output.append(result.getSentenceWithLongestWord()).append("\n");
            }
        }

        output.append("\n"); // Отступ между разными запусками

        return output.toString();
    }

    /**
     * Создает пример текстового файла для тестирования
     */
    public static void createSampleFile(String filePath) throws IOException {
        String sampleText = """
            Это пример текста для анализа. Он содержит несколько предложений!
            Мы проверим, как работает программа: подсчет слов, поиск длинных предложений и слов.
            Самое длинное слово в этом тексте должно быть выделено.
            
            Второй абзац с дополнительным текстом. Здесь тоже есть предложения разной длины?
            Да, конечно! Программа должна корректно обрабатывать различные знаки препинания.
            
            Третий абзац для проверки работы с кириллицей и разными кодировками.
            Все должно работать правильно в операционной системе Windows.
            """;

        // Записываем в кодировке Windows-1251 (стандартная для Windows)
        Files.write(
                Paths.get(filePath),
                sampleText.getBytes("Windows-1251")
        );

        System.out.println("Создан пример файла: " + filePath);
    }
}