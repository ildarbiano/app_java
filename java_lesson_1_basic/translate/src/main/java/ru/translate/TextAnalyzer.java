package ru.translate;

import java.util.*;
import java.util.regex.*;

public class TextAnalyzer {

    public AnalysisResult analyze(String text) {
        AnalysisResult result = new AnalysisResult();

        // Нормализуем текст: заменяем множественные пробелы и переносы строк
        text = normalizeText(text);

        // Разбиваем на предложения
        List<String> sentences = splitIntoSentences(text);
        result.setSentenceCount(sentences.size());
        result.setAllSentences(sentences);

        // Разбиваем на слова и находим самое длинное слово
        List<String> words = extractWords(text);
        result.setWordCount(words.size());
        result.setAllWords(words);

        String longestWord = findLongestWord(words);
        result.setLongestWord(longestWord);

        // Находим самые длинные предложения
        List<String> longestSentences = findLongestSentences(sentences);
        result.setLongestSentences(longestSentences);

        // Находим предложение с самым длинным словом
        String sentenceWithLongestWord = findSentenceWithWord(sentences, longestWord);
        result.setSentenceWithLongestWord(sentenceWithLongestWord);

        return result;
    }

    private String normalizeText(String text) {
        // Заменяем все виды пробельных символов на обычный пробел
        return text.replaceAll("\\s+", " ").trim();
    }

    private List<String> splitIntoSentences(String text) {
        List<String> sentences = new ArrayList<>();
        if (text == null || text.trim().isEmpty()) {
            return sentences;
        }

        // Используем регулярное выражение для разбиения на предложения
        // Учитываем . ! ? и возможность кавычек в конце предложения
        String regex = "(?<=[.!?])\\s+(?=[А-ЯA-Z])";
        String[] parts = text.split(regex);

        for (String part : parts) {
            String sentence = part.trim();
            if (!sentence.isEmpty()) {
                sentences.add(sentence);
            }
        }

        return sentences;
    }

    private List<String> extractWords(String text) {
        List<String> words = new ArrayList<>();

        // Регулярное выражение для извлечения слов (кириллица, латиница, цифры, дефис, апостроф)
        String wordRegex = "[А-Яа-яЁёA-Za-z\\d'-]+";
        Pattern pattern = Pattern.compile(wordRegex);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String word = matcher.group();
            // Исключаем слова, состоящие только из цифр
            if (!word.matches("\\d+")) {
                words.add(word);
            }
        }

        return words;
    }

    private String findLongestWord(List<String> words) {
        String longest = "";
        for (String word : words) {
            if (word.length() > longest.length()) {
                longest = word;
            }
        }
        return longest;
    }

    private List<String> findLongestSentences(List<String> sentences) {
        List<String> longestSentences = new ArrayList<>();
        if (sentences.isEmpty()) {
            return longestSentences;
        }

        // Находим максимальную длину
        int maxLength = 0;
        for (String sentence : sentences) {
            if (sentence.length() > maxLength) {
                maxLength = sentence.length();
            }
        }

        // Собираем все предложения максимальной длины
        for (String sentence : sentences) {
            if (sentence.length() == maxLength) {
                longestSentences.add(sentence);
            }
        }

        return longestSentences;
    }

    private String findSentenceWithWord(List<String> sentences, String targetWord) {
        if (targetWord == null || targetWord.isEmpty()) {
            return null;
        }

        for (String sentence : sentences) {
            // Ищем слово целиком, используя границы слова
            String regex = "\\b" + Pattern.quote(targetWord) + "\\b";
            Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            if (pattern.matcher(sentence).find()) {
                // Выделяем найденное слово заглавными буквами
                return sentence.replaceAll("(?i)\\b" + Pattern.quote(targetWord) + "\\b",
                        targetWord.toUpperCase());
            }
        }

        return null;
    }
}
