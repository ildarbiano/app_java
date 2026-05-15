package edu.innotech.unittest;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class Tests {

    @Test                       //Аннотация для Junit
    void marksInRange() {
        List<Integer> lst = List.of(2, 3, 4, 5);

        Student stud = new Student("Ivan");

        for (int mark : lst) {
            stud.addMark(mark);
        }

        assertEquals(lst, stud.getMark());
    }

    @Test                           //Аннотация для Junit
    void markNotInRange() {
        Student stud = new Student("Vasil");

        assertThrows(IllegalArgumentException.class, () -> {
            stud.addMark(0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            stud.addMark(1);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            stud.addMark(6);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            stud.addMark(7);
        });
    }
}