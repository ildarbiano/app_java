package lesson_5.src.main.java;
import lesson_5.src.main.ru.inno.lesson.My2Class_4;

public class My2Class {
    public static void main(String[] args) {
        System.out.println("--- Testing class: MySecondClass. obj1 ---");

        My2Class_4 obj1 = new My2Class_4(0, 0);

        obj1.setA(5);
        obj1.setB(3);

        System.out.println("obj1: a = " + obj1.getA() + ", b = " + obj1.getB());

        int result1 = obj1.sum();
        System.out.println("Result for: obj1: " + result1);
        System.out.println("Checking: 5 + 3 = " + (5 + 3));
        System.out.println("Result: " + (result1 == 8));

        System.out.println("\n--- Another Object. obj2 ---");

        My2Class_4 obj2 = new My2Class_4(10, 7);

        System.out.println("obj2: a = " + obj2.getA() + ", b = " + obj2.getB());

        int result2 = obj2.sum();
        System.out.println("Result for:obj2: " + result2);
        System.out.println("Checking: 10 + 7 = " + (10 + 7));
        System.out.println("Result: " + (result2 == 17));

        System.out.println("\n--- testing. obj2 after changing ---");

        obj2.setA(20);
        obj2.setB(30);
        System.out.println("obj2 after changing: a = " + obj2.getA() + ", b = " + obj2.getB());
        System.out.println("New result: " + obj2.sum());
    }
}
