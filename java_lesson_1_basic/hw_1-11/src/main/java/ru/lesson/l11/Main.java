package ru.lesson.l11;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("-----Nothing to do-----");
            return;
        }

        String input = args[0];

        String[] words = input.split("\\s+");

        int wordCount = words.length;
        int maxLength = findMaxWordLength(words);
        List<String> longestWords = findLongestWords(words, maxLength);

        System.out.println("Input was:");
        System.out.println(input);
        System.out.println();

        System.out.println("How much words in this phrase: = " + wordCount);
        System.out.println("The longest word length: = " + maxLength + " symbols");

        System.out.println("The longest word(s):");
        for (String word : longestWords) {
            System.out.println("- " + word);
        }
    }

    // 1️⃣ Максимальная длина слова
    private static int findMaxWordLength(String[] words) {
        int max = words[0].length();
        for (String word : words) {
            if (word.length() > max) {
                max = word.length();
            }
        }
        return max;
    }

    // 2️⃣ Все слова максимальной длины
    private static List<String> findLongestWords(String[] words, int maxLength) {
        List<String> result = new ArrayList<>();
        for (String word : words) {
            if (word.length() == maxLength) {
                result.add(word);
            }
        }
        return result;
    }
}
