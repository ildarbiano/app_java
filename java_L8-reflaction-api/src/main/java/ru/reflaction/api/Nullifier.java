package ru.reflaction.api;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

public class Nullifier {

    // Универсальный метод для "обнуления" объекта (присваивает полям значения по умолчанию)
    public static void nullify(Object obj) {
        if (obj == null) return;

        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            // Игнорируем статические поля
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }

            field.setAccessible(true); // Даём доступ к private полям

            try {
                Class<?> type = field.getType();

                if (type.isPrimitive()) {
                    // Устанавливаем значение по умолчанию для примитивов
                    if (type == boolean.class) {
                        field.setBoolean(obj, false);
                    } else if (type == byte.class) {
                        field.setByte(obj, (byte) 0);
                    } else if (type == short.class) {
                        field.setShort(obj, (short) 0);
                    } else if (type == int.class) {
                        field.setInt(obj, 0);
                    } else if (type == long.class) {
                        field.setLong(obj, 0L);
                    } else if (type == float.class) {
                        field.setFloat(obj, 0.0f);
                    } else if (type == double.class) {
                        field.setDouble(obj, 0.0);
                    } else if (type == char.class) {
                        field.setChar(obj, '\u0000');
                    }
                } else {
                    // Ссылочные поля устанавливаем в null
                    field.set(obj, null);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Не удалось обнулить поле " + field.getName(), e);
            }
        }
    }

    public static void main(String[] args) {
        // Создаём объект Cat
        Cat cat = new Cat("Василий", 10, new ArrayList<>(Arrays.asList("Антон", "Oleg", "Игорь")));
        System.out.println("----------------");
        System.out.println("До обнуления Cat:");
        System.out.println(cat);

        // Обнуляем Cat
        nullify(cat);

        System.out.println("\nПосле обнуления Cat:");
        System.out.println(cat);
        System.out.println("-----------------------------------------");

        // Создаём объект Dog для самопроверки
        Dog dog = new Dog("Бобик", 5, new ArrayList<>(Arrays.asList("Джэк", "Шарик")));
        System.out.println("----------------");
        System.out.println("До обнуления Dog:");
        System.out.println(dog);

        // Обнуляем Dog
        nullify(dog);

        System.out.println("\nПосле обнуления Dog:");
        System.out.println(dog);
        System.out.println("-----------------------------------------");
    }
}

// Класс Cat по условию
class Cat {
    private static String breed = "Persian"; // статическое поле, не обнуляем
    public String name;
    private int age;
    private List<String> friendsName;

    public Cat(String name, int age, List<String> friendsName) {
        this.name = name;
        this.age = age;
        this.friendsName = friendsName;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", friendsName=" + friendsName +
                '}';
    }
}

// Похожий класс Dog для самопроверки
class Dog {
    private static String breed = "Labrador";
    public String name;
    private int age;
    private List<String> friendsName;

    public Dog(String name, int age, List<String> friendsName) {
        this.name = name;
        this.age = age;
        this.friendsName = friendsName;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", friendsName=" + friendsName +
                '}';
    }
}