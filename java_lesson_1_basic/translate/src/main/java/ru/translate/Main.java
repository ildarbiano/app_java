package ru.translate;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String inputPath = "C:\\app\\java_lesson\\translate\\input.txt";
        String outputPath = "C:\\app\\java_lesson\\translate\\output.txt";

        try {
            // Читаем текст из файла
            String text = ru.translate.FileHandler.readFile(inputPath);

            // Анализируем текст
            ru.translate.TextAnalyzer analyzer = new ru.translate.TextAnalyzer();
            AnalysisResult result = analyzer.analyze(text);

            // Записываем результаты в файл
            FileHandler.writeResults(outputPath, result);

            System.out.println("✓ Анализ завершен успешно!");
            System.out.println("✓ Результаты сохранены в: " + outputPath);

        } catch (IOException e) {
            System.err.println("✗ Ошибка при работе с файлами: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("✗ Ошибка при анализе текста: " + e.getMessage());
            e.printStackTrace();
        }
    }
}