package ru.stream.first;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.List;

public class StreamToStringDemo {

    /**
     * Преобразует поток строк в одну строку, объединяя элементы через пробел.
     * Порядок слов соответствует порядку элементов в потоке.
     *
     * @param stream поток строк (не должен содержать null)
     * @return строка, содержащая все элементы потока, разделённые пробелом
     */
    public static String getStringFromStream(Stream<String> stream) {
        return stream.collect(Collectors.joining(" "));
    }

    public static void main(String[] args) {
        // Пример 1: поток из нескольких слов
        Stream<String> stream1 = Stream.of("Hello", "world", "from", "Java");
        String result1 = getStringFromStream(stream1);
        System.out.println("Пример 1, (Поток из нескольких слов): " + result1);

        // Пример 2: поток из одного слова
        Stream<String> stream2 = Stream.of("Single");
        String result2 = getStringFromStream(stream2);
        System.out.println("Пример 2, (Поток из одного слова): " + result2);

        // Пример 3: пустой поток
        Stream<String> stream3 = Stream.empty();
        String result3 = getStringFromStream(stream3);
        System.out.println("Пример 3, (Пустой поток): \"" + result3 + "\" <<<< нет входящих данных, поэтому кавычки пустые");

        // Пример 4: поток из списка
        List<String> words = List.of("Lambda", "streams", "это", "круто");
        Stream<String> stream4 = words.stream();
        String result4 = getStringFromStream(stream4);
        System.out.println("Пример 4, (Поток из списка): " + result4);
    }
}