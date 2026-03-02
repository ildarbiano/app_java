package ru.lesson.l_12;

import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Usage: java ru.lesson.l_12.Main <length>");
            System.exit(1);
        }

        int x;
        try {
            x = Integer.parseInt(args[0]);
            if (x < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            System.err.println("Invalid length: " + args[0]);
            System.exit(2);
            return;
        }

        String pool = "qrstuvwxyz6789?!;"; // набор допустимых символов
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        StringBuilder sb = new StringBuilder(x + x / 5 + 1);

        for (int i = 0; i < x; i++) {
            char c = pool.charAt(rnd.nextInt(pool.length()));
            sb.append(c);
            // вставляем пробел после каждых 5 символов (и не в конце строки)
            if ((i + 1) % 5 == 0 && i != x - 1) {
                sb.append(' ');
            }
        }

        // заменяем все вхождения '?' на '_'
        String result = sb.toString().replace('?', '_');

        System.out.println(result);
    }
}
