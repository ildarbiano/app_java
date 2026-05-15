package edu.innotech.unittest;

import org.junit.jupiter.api.*;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class Tests {
    @BeforeEach
    public void createFile() {
        System.out.println("Create file");
    }
    @AfterEach
    public void deleteFile() {
        System.out.println("Delete file");
    }
    @Test        //Аннотация для Junit
    @DisplayName("корректные оценки добавляются в список оценок")
    void marksInRange() {
        List<Integer> lst = List.of(2, 3, 4, 5);
        Student stud = new Student("Ivan");
        stud.addMark(lst.get(0));
        stud.addMark(lst.get(1));
        stud.addMark(lst.get(2));
        stud.addMark(lst.get(3));
        Assertions.assertEquals(stud.getMark(), lst);
    }

    @Test        //Аннотация для Junit
    @DisplayName("добавление неверных оценок, - возвращает исключение")
    void markNotInRange() {
        Student stud = new Student("Vasil");

        assertThrows(IllegalArgumentException.class, () -> stud.addMark(0));
        assertThrows(IllegalArgumentException.class, () -> stud.addMark(1));
        assertThrows(IllegalArgumentException.class, () -> {
            stud.addMark(6);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            stud.addMark(7);
        });
    }
}