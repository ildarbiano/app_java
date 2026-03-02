package lesson_5;

import lesson_5.src.main.ru.inno.lesson.My2Class_4;

public class My2Class_comments {
    public static void main(String[] args) {
        System.out.println("Тестирование класса MySecondClass");

        //  Создание объекта с нулевыми значениями
        //  КОНСТРУКТОР КЛАССА вызывается оператором new, с параметрами 0 и 0
        My2Class_4 obj1 = new My2Class_4(0, 0);

        //  Установка значений через сеттеры
        obj1.setA(5);
        obj1.setB(3);

        System.out.println("obj1: a = " + obj1.getA() + ", b = " + obj1.getB());

        //  Вызов метода сложения и вывод результата
        int result1 = obj1.sum();
        System.out.println("Результат сложения для obj1: " + result1);
        System.out.println("Проверка: 5 + 3 = " + (5 + 3));
        System.out.println("Результат верный: " + (result1 == 8));

        System.out.println("\n--- Создание второго объекта ---");

        //  КОНСТРУКТОР КЛАССА вызывается оператором new, с параметрами 10 и 7
        //  Создание нового экземпляра объекта по конструктору с параметрами:
        My2Class_4 obj2 = new My2Class_4(10, 7);

        System.out.println("obj2: a = " + obj2.getA() + ", b = " + obj2.getB());

        //  Вызов метода сложения для второго объекта
        int result2 = obj2.sum();
        System.out.println("Результат сложения для obj2: " + result2);
        System.out.println("Проверка: 10 + 7 = " + (10 + 7));
        System.out.println("Результат верный: " + (result2 == 17));

        // Дополнительные тесты
        System.out.println("\n--- Дополнительные тесты ---");

        //  Изменение значений через сеттеры
        obj2.setA(20);
        obj2.setB(30);
        System.out.println("obj2 после изменения: a = " + obj2.getA() + ", b = " + obj2.getB());
        System.out.println("Новая сумма: " + obj2.sum());
    }
}
