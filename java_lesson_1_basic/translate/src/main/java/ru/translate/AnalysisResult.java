package ru.translate;

import java.util.ArrayList;
import java.util.List;

public class AnalysisResult {
    private int sentenceCount;
    private int wordCount;
    private String longestWord;
    private String sentenceWithLongestWord;
    private List<String> longestSentences;
    private List<String> allSentences;
    private List<String> allWords;

    public AnalysisResult() {
        this.sentenceCount = 0;
        this.wordCount = 0;
        this.longestWord = "";
        this.sentenceWithLongestWord = "";
        this.longestSentences = new ArrayList<>();
        this.allSentences = new ArrayList<>();
        this.allWords = new ArrayList<>();
    }

    // Геттеры и сеттеры
    public int getSentenceCount() {
        return sentenceCount;
    }

    public void setSentenceCount(int sentenceCount) {
        this.sentenceCount = sentenceCount;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    public String getLongestWord() {
        return longestWord;
    }

    public void setLongestWord(String longestWord) {
        this.longestWord = longestWord;
    }

    public String getSentenceWithLongestWord() {
        return sentenceWithLongestWord;
    }

    public void setSentenceWithLongestWord(String sentenceWithLongestWord) {
        this.sentenceWithLongestWord = sentenceWithLongestWord;
    }

    public List<String> getLongestSentences() {
        return longestSentences;
    }

    public void setLongestSentences(List<String> longestSentences) {
        this.longestSentences = longestSentences;
    }

    public List<String> getAllSentences() {
        return allSentences;
    }

    public void setAllSentences(List<String> allSentences) {
        this.allSentences = allSentences;
    }

    public List<String> getAllWords() {
        return allWords;
    }

    public void setAllWords(List<String> allWords) {
        this.allWords = allWords;
    }

    @Override
    public String toString() {
        return String.format(
                "AnalysisResult{sentences=%d, words=%d, longestWord='%s', longestSentences=%d}",
                sentenceCount, wordCount, longestWord, longestSentences.size()
        );
    }
}